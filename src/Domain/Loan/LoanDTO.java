package Domain.Loan;

import java.util.Date;

import lib.ERDataResources;
public class LoanDTO extends ERDataResources{

    public int id;
    public int user_id;
    public String object_id;
    public Date loan_date;
    public boolean returned;

    public LoanDTO() {}

    public LoanDTO(int id, int user_id, String object_id, Date loan_date) {
        this.id = id;
        this.user_id = user_id;
        this.object_id = object_id;
        this.loan_date = loan_date;
    }

    public void showDTO() {
		IO.println("======= Dados do Empréstimo =======");
		IO.println("ID: " + this.id);
		IO.println("Realizada por (ID do Usuário): " + this.user_id);
		IO.println("ID do Objeto: " + this.object_id);
		IO.println("===================================");
	}

}
