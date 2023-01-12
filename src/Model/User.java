package Model;

import Repository.IModel;
import Unit.DateUnit;

import java.util.Date;

public class User implements IModel<User> {

    private long id;
    private String name;
    private String userName;
    private String password;
    private String mobile;
    private String address;
    private EGender eGender;
    private EPosition ePosition;
    private Date creatDate;
    public User(){

    }

    public User(long id, String name, String userName, String password, String mobile, String address, EGender eGender, EPosition ePosition, Date creatDate) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.mobile = mobile;
        this.address = address;
        this.eGender = eGender;
        this.ePosition = ePosition;
        this.creatDate = creatDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public EGender getEGender() {
        return eGender;
    }

    public void setEGender(EGender eGender) {
        this.eGender = eGender;
    }

    public EPosition getEPosition() {
        return ePosition;
    }

    public void setEPosition(EPosition ePosition) {
        this.ePosition = ePosition;
    }

    public Date getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(Date creatDate) {
        this.creatDate = creatDate;
    }
    //ID, name,username, pw,SDT,địa chỉ, ngày tạo, Egender, Epos.
    @Override
    public void update(User objNew) {
        this.userName = objNew.getUserName();
        this.password = objNew.getPassword();
        this.name = objNew.getName();
        this.mobile = objNew.getMobile();
        this.address = objNew.getAddress();
        this.eGender = objNew.getEGender();
        this.creatDate = objNew.getCreatDate();
    }
    @Override
    public User parseData(String line) {
        String [] items = line.split(",");
        long id = Long.parseLong(items[0]);
        EGender gender = EGender.getEGenderByName(items[6].trim());
        EPosition pos = Model.EPosition.getEPositionByName(items[7].trim());
        Date creat = DateUnit.convertStringToDateAndHour(items[8]);
        User user =new User(id,items[1],items[2],items[3],items[4],items[5],gender,pos, creat);
        return user;
    }
    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s", this.getId(), this.getName(),this.getUserName(), this.getPassword()
                , this.getMobile(),this.getAddress(), String.valueOf(this.getEGender()), String.valueOf(this.getEPosition())
                , DateUnit.convertDateAndHourToString(this.creatDate));
    }
    public String toView() {
        String strDate = DateUnit.convertDateAndHourToString(this.creatDate);
        return String.format("%10s|%20s|%15s|%15s|%15s|%15s|%12s|%12s|%20s", this.id,this.name, this.userName, this.password, this.mobile, this.address, this.eGender, this.ePosition,strDate);
    }



}
