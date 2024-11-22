package org.server.apimonitoreo.controller;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.server.apimonitoreo.models.dtos.save.CoordenadaDtoSave;
import org.server.apimonitoreo.models.dtos.send.SensorDtoSend;
import org.server.apimonitoreo.models.dtos.webSocket.Mensaje;
import org.server.apimonitoreo.models.dtos.webSocket.SendMensaje;
import org.server.apimonitoreo.models.entities.Bovino;
import org.server.apimonitoreo.models.entities.Usuario;
import org.server.apimonitoreo.service.BovinoService;
import org.server.apimonitoreo.service.SensorService;
import org.server.apimonitoreo.service.ValidationCoordenadas;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Optional;
import java.util.UUID;


@AllArgsConstructor
@Controller
public class DatosWsController {
    private final SimpMessagingTemplate messagingTemplate;
    private final SensorService sensorService;
    private final BovinoService bovinoService;
    private final ValidationCoordenadas validationCoordenadas;

    @MessageMapping("/datos")
    @SendTo("/topic/datos")
    public SendMensaje datos(Mensaje mensaje, StompHeaderAccessor stompHeaderAccessor) throws Exception {
        return new SendMensaje(UUID.randomUUID(), mensaje.getLatitud(), mensaje.getLongitud());
    }

    @Transactional
    @MessageMapping("/datos/id")
    public void datosReceive(StompHeaderAccessor stompHeaderAccessor, Mensaje mensaje) {
        SensorDtoSend sensorSave;
        Optional<SensorDtoSend> sensor;
        System.out.println("Datos recibidos: " + mensaje.getLatitud() + " " + mensaje.getLongitud() + " id: " + mensaje.getId());

        try {
            if (mensaje.getLongitud() == 0) {
                System.out.println("Conexion de prueba del sensor");
                return;
            } else {
                UUID id = mensaje.getId();
                System.out.println("Buscamos el sensor con id: " + id);
                sensor = sensorService.findById(id);

                if (sensor.isEmpty()) {
                    System.out.println("Sensor no encontrado, creando uno nuevo.");
                    sensorSave = sensorService.save();
                    sensor = Optional.of(sensorSave);
                } else {
                    System.out.println("Sensor encontrado: " + sensor.get().getId());
                }
            }

            UUID idSensor = sensor.get().getId();
            System.out.println("idSensor: " + idSensor);

            Bovino bovino = bovinoService.findBySensor_Id(idSensor);
            validationCoordenadas.alerta(new CoordenadaDtoSave(mensaje.getLatitud(), mensaje.getLongitud()), bovino);

            System.out.println("Bovino encontrado: " + bovino.getCodigo());
            Hibernate.initialize(bovino.getHistorialUbicaciones());
            Usuario propietario = bovinoService.findByPropietario(bovino.getCodigo());
            Usuario capataz = bovinoService.findByCapataz(bovino.getCodigo());
            System.out.println("Propietario encontrado: " + propietario.getNombre()+" "+propietario.getId());
            System.out.println("Capataz acargo del bovino: " + capataz.getNombre()+" "+capataz.getId());
            messagingTemplate.convertAndSend("/topic/datos/id/" + propietario.getId(),
                    new SendMensaje(idSensor, mensaje.getLatitud(), mensaje.getLongitud()));
            messagingTemplate.convertAndSend("/topic/datos/id/" + capataz.getId(),
                    new SendMensaje(idSensor, mensaje.getLatitud(), mensaje.getLongitud()));
            System.out.println("Enviado datos a: /topic/datos/id/" + propietario.getId());
        } catch (Exception e) {
            System.out.println("Detalles: " + e.getMessage());
        } finally {
            messagingTemplate.convertAndSend("/topic/datos/", mensaje.getId());
        }
    }

    @EventListener
    public void handleConexionEvent(SessionConnectEvent event) {
        System.out.println("cliente conectado");
    }
    @EventListener
    public void handleDisConexionEvent(SessionDisconnectEvent event) {
        System.out.println("cliente desconectado");
    }
}
