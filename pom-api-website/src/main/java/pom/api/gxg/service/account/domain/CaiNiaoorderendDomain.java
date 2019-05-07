package pom.api.gxg.service.account.domain;

public class CaiNiaoorderendDomain {

    public int id;
    public String printData;
    public String cpCode;
    public String cpName;
    public String orderId;
    public String mailNo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrintData() {
        return printData;
    }

    public void setPrintData(String printData) {
        this.printData = printData;
    }

    public String getCpCode() {
        return cpCode;
    }

    public void setCpCode(String cpCode) {
        this.cpCode = cpCode;
    }

    public String getCpName() {
        return cpName;
    }

    public void setCpName(String cpName) {
        this.cpName = cpName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getMailNo() {
        return mailNo;
    }

    public void setMailNo(String mailNo) {
        this.mailNo = mailNo;
    }
}
