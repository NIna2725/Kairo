package com.mediahunters.kairo.service.recognition;

/**
 * Representa la respuesta estándar de los endpoints de reconocimiento,
 * combinando el resultado obtenido por los motores (audio, video o texto)
 * y el contexto recuperado desde la base de datos cuando está disponible.
 */
public class RecognitionResponse {

    private final String resultado;
    private final String contexto;

    public RecognitionResponse(String resultado, String contexto) {
        this.resultado = resultado;
        this.contexto = contexto;
    }

    public static RecognitionResponse soloResultado(String resultado) {
        return new RecognitionResponse(resultado, null);
    }

    public String getResultado() {
        return resultado;
    }

    public String getContexto() {
        return contexto;
    }
}