package org.server.apimonitoreo.service.impl;

import lombok.AllArgsConstructor;
import org.server.apimonitoreo.models.dtos.save.CoordenadaDtoSave;
import org.server.apimonitoreo.models.entities.Bovino;
import org.server.apimonitoreo.models.entities.Coordenada;
import org.server.apimonitoreo.models.entities.Usuario;
import org.server.apimonitoreo.service.EmailService;
import org.server.apimonitoreo.service.ValidationCoordenadas;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ValidationCoordenadasImpl implements ValidationCoordenadas {

    private final EmailService emailService;

    @Override
    public void alerta(CoordenadaDtoSave coordenada, Bovino bovino) {
        List<Coordenada> coordenadas = bovino.getPotrero().getCoordenadas();
        Usuario propietario = bovino.getPropietario();
        Usuario capataz = bovino.getPotrero().getFinca().getCapataz();

        String htmlMessage = """
            <html>
            <head>
                <style>
                    body {
                        font-family: Arial, sans-serif;
                        background-color: #f4f4f9;
                        color: #333333;
                        margin: 0;
                        padding: 0;
                    }
                    .email-container {
                        max-width: 600px;
                        margin: 20px auto;
                        background-color: #ffffff;
                        border: 1px solid #dddddd;
                        border-radius: 8px;
                        box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
                        overflow: hidden;
                    }
                    .email-header {
                        background-color: #ff6b6b;
                        color: #ffffff;
                        padding: 20px;
                        text-align: center;
                        font-size: 24px;
                        font-weight: bold;
                    }
                    .email-content {
                        padding: 20px;
                        color: #333333;
                        line-height: 1.6;
                    }
                    .email-content h2 {
                        color: #ff6b6b;
                        font-size: 20px;
                        margin-bottom: 10px;
                    }
                    .email-content p {
                        margin: 10px 0;
                    }
                    .email-details {
                        margin: 20px 0;
                        padding: 10px;
                        border: 1px solid #eeeeee;
                        border-radius: 5px;
                        background-color: #f9f9f9;
                    }
                    .email-details ul {
                        list-style-type: none;
                        padding: 0;
                        margin: 0;
                    }
                    .email-details li {
                        margin: 5px 0;
                    }
                    .email-footer {
                        background-color: #333333;
                        color: #ffffff;
                        text-align: center;
                        padding: 15px;
                        font-size: 12px;
                    }
                </style>
            </head>
            <body>
                <div class="email-container">
                    <div class="email-header">🚨 Alerta: Bovino fuera del potrero</div>
                    <div class="email-content">
                        <h2>El bovino ha salido del área designada</h2>
                        <p>El sistema ha detectado que el bovino con ID <strong>%s</strong> se encuentra fuera de los límites del potrero.</p>
                        <div class="email-details">
                            <p><strong>Detalles del bovino y contactos de emergencia:</strong></p>
                            <ul>
                                <li><strong>Propietario:</strong> %s (%s)</li>
                                <li><strong>Capataz:</strong> %s (%s)</li>
                                <li><strong>Ubicación actual:</strong> Latitud: %s, Longitud: %s</li>
                            </ul>
                        </div>
                        <p>Por favor, tome las medidas necesarias para asegurar el retorno del bovino al área segura.</p>
                        <p>BovinTrack</p>
                    </div>
                </div>
            </body>
            </html>
            """.formatted(
                bovino.getCodigo(),
                propietario.getNombre(), propietario.getCorreo(),
                capataz.getNombre(), capataz.getCorreo(),
                coordenada.getLatitud(), coordenada.getLongitud()
        );



        if (!isValid(coordenada, coordenadas)) {
            System.out.println("Alerta: Bovino fuera del potrero");
            emailService.sendEmail(new String[]{propietario.getCorreo(), capataz.getCorreo()},
                    "Alerta: Bovino fuera del potrero",
                    htmlMessage);
        }
    }

    @Override
    public boolean isValid(CoordenadaDtoSave coordenada, List<Coordenada> poligono) {
        int numVertices = poligono.size();
        boolean inside = false;
        double latPunto = coordenada.getLatitud();
        double lngPunto = coordenada.getLongitud();

        for (int i = 0, j = numVertices - 1; i < numVertices; j = i++) {
            double latVerticeActual = poligono.get(i).getLatitud();
            double lngVerticeActual = poligono.get(i).getLongitud();
            double latVerticeAnterior = poligono.get(j).getLatitud();
            double lngVerticeAnterior = poligono.get(j).getLongitud();

            boolean interseccion = ((latVerticeActual > latPunto) != (latVerticeAnterior > latPunto)) &&
                    (lngPunto < (lngVerticeAnterior - lngVerticeActual) * (latPunto - latVerticeActual) /
                            (latVerticeAnterior - latVerticeActual) + lngVerticeActual);

            if (interseccion) {
                inside = !inside;
            }
        }
        return inside;
    }


}
