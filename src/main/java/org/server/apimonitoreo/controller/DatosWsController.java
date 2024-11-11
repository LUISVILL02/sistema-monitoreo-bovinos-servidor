package org.server.apimonitoreo.controller;

import lombok.AllArgsConstructor;
import org.server.apimonitoreo.models.dtos.send.SensorDtoSend;
import org.server.apimonitoreo.models.dtos.webSocket.Mensaje;
import org.server.apimonitoreo.models.dtos.webSocket.SendMensaje;
import org.server.apimonitoreo.service.SensorService;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Optional;
import java.util.UUID;

import static java.util.Objects.isNull;


@AllArgsConstructor
@Controller
public class DatosWsController {
    private final SimpMessagingTemplate messagingTemplate;
    private final SensorService sensorService;


    @MessageMapping("/datos/id")
    public void datosReceive(StompHeaderAccessor stompHeaderAccessor, Mensaje mensaje) {
        SensorDtoSend sensorSave = new SensorDtoSend();
        Optional<SensorDtoSend> sensor = Optional.empty();
        System.out.println("Datos recibidos: " + mensaje.getLatitud() + " " + mensaje.getLongitud() + " id: " + mensaje.getId());
        try {
            if (mensaje.getLongitud() == 0){
                System.out.println("Conexion de prueba del sensor");
                return;
            }else{
                UUID id = mensaje.getId();
                sensor = sensorService.findById(id);
            }
        }catch (Exception e){
            System.out.println("Sensor no encontrado. Mas detalles: "+e.getMessage());
            sensorSave = sensorService.save();
        }
        UUID idSensor = (isNull(sensorSave.getId())) ? sensor.get().getId() : sensorSave.getId();
        System.out.println("idSensor: "+idSensor);
        messagingTemplate.convertAndSend("/topic/datos/", idSensor);
        messagingTemplate.convertAndSend("/topic/datos/id/",
                new SendMensaje(idSensor, mensaje.getLatitud(), mensaje.getLongitud()));
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
