package aggregates.Alocation;

import java.util.Date;

public class AlocationDTO {
  public int id;
  public int user_id;
  public int type_id;
  public int object_id;
  public int object_name;
  public String user_alocator;
  public Date inicial_date;
  
  public Date last_modification;
  public boolean deleted;

  public AlocationDTO(int id,
                      int user_id,
                      int type_id,
                      int object_id,
                      int object_name,
                      String user_alocator,
                      Date inicial_date,
                      boolean deleted) {
    this.id = id;
    this.user_id = user_id;
    this.type_id = type_id;
    this.object_id = object_id;
    this.object_name = object_name;
    this.user_alocator = user_alocator;
    this.inicial_date = inicial_date;
    this.deleted = deleted;
  }




}
