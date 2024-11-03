package org.server.apimonitoreo.controller;

import lombok.AllArgsConstructor;
import org.server.apimonitoreo.models.dtos.send.SensorDtoSend;
import org.server.apimonitoreo.models.dtos.webSocket.Mensaje;
import org.server.apimonitoreo.models.dtos.webSocket.SendMensaje;
import org.server.apimonitoreo.service.SensorService;
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

import static java.util.Objects.isNull;


@AllArgsConstructor
@Controller
public class DatosWsController {
    private final SimpMessagingTemplate messagingTemplate;
    private final SensorService sensorService;

    @MessageMapping("/datos")
    @SendTo("/topic/datos")
    public SendMensaje datos(Mensaje mensaje, StompHeaderAccessor stompHeaderAccessor) throws Exception {
        return new SendMensaje(mensaje.getId(), mensaje.getLatitud(), mensaje.getLongitud());
    }

    @MessageMapping("/datos/id")
    public void datosReceive(StompHeaderAccessor stompHeaderAccessor, Mensaje mensaje) {
        SensorDtoSend sensorSave = new SensorDtoSend();
        Optional<SensorDtoSend> sensor = Optional.empty();
        System.out.println("Datos recibidos: " + mensaje.getLatitud() + " " + mensaje.getLongitud() + " id: " + mensaje.getId());
        try {
            sensor = sensorService.findById(mensaje.getId());
        }catch (Exception e){
            sensorSave = sensorService.save();
        }
        UUID idSensor = (isNull(sensorSave.getId())) ? sensor.get().getId() : sensorSave.getId();
        //tengo que enviar los datos al sensor ya que el topic es para el cliente que recibe la ubicación
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
