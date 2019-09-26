package com.github.vnduda.equals;

public class NumeroTelefonico {

    private String codigoEstado;
    private String codigoPais;
    private String numeroDoTelefone;

    public NumeroTelefonico(final String codigoPais, final String codigoEstado, final String numeroDoTelefone) {
        this.codigoPais = codigoPais;
        this.codigoEstado = codigoEstado;
        this.numeroDoTelefone = numeroDoTelefone;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        NumeroTelefonico outro = (NumeroTelefonico) obj;

        // FIXME Orientei de forma enganosa. O Werberth perguntou a diferença entre == e equals, eu expliquei. Contudo,
        // na hora em que estave "implementando" sugeri equivocadamente o uso do ==. Com este equals, seu teste deve falhar!!!
        return codigoEstado == outro.codigoEstado && codigoPais == outro.codigoPais && numeroDoTelefone == outro.numeroDoTelefone;
    }

    public int hashCode() {
        return codigoPais.hashCode() + codigoEstado.hashCode() + numeroDoTelefone.hashCode();
    }
}
