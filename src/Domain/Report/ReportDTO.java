package Domain.Report;

import java.util.Date;

public class ReportDTO {

    public int loan_id;
    public String user_name;
    public String object_name;
    public String type_name;
    public String loan_status;
    public Date loan_date;

    public ReportDTO() {}

    public ReportDTO(int loan_id, String user_name, String object_name, String type_name, String loan_status, Date loan_date) {
        this.loan_id = loan_id;
        this.user_name = user_name;
        this.object_name = object_name;
        this.type_name = type_name;
        this.loan_status = loan_status;
        this.loan_date = loan_date;
    }
}

