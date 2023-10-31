package models;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the consecutivo database table.
 * 
 */
@Entity
@Table(name = "consecutivo")
@NamedQuery(name = "Consecutivo.findAll", query = "SELECT c FROM Consecutivo c")
public class Consecutivo implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -8811584746517003884L;

  @Id
  private String tabla;

  private long valor;

  public Consecutivo() {
  }

  public String getTabla() {
    return this.tabla;
  }

  public void setTabla(String tabla) {
    this.tabla = tabla;
  }

  public long getValor() {
    return this.valor;
  }

  public void setValor(long valor) {
    this.valor = valor;
  }

}