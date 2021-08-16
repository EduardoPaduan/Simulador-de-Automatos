public class Automato {
    private String estado;
    private String simbolo;
    private String novoEstado;

    public Automato(String estado, String simbolo, String novoEstado) {
        this.estado = estado;
        this.simbolo = simbolo;
        this.novoEstado = novoEstado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getNovoEstado() {
        return novoEstado;
    }

    public void setNovoEstado(String novoEstado) {
        this.novoEstado = novoEstado;
    }
}
