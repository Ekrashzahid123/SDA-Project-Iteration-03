/*Main class                                    passanger extends user                                   Driver extends user
 
1..User                                        bookRide(pickuplocation,dropofflocation)               acceptRide(rideId)
attributes
struing id                                     makepayment(paymentMethod)                             rejectRide(rideId)         
string name
string email                                   Trackdriver(DriverId)                                  updateLocation(passengerId)
string phone
string passward                                rateDriver(rating,feedback)                            ratePassanger(rating,feedback)
fun Manageprofile()

*/ 
//Work done by Ekrash Zahid
//Fork by Abrar Tahir123
 class User {
 private String id;
 private String name;
 private String email;
 private String phone;
 private String password;

 public User()
 {
    id=" ";
    name=" ";
    email=" ";
    phone=" ";
    password=" ";
 }
 public User(String id, String name, String email, String phone, String password)
 {
    this.id=id;
    this.name=name;
    this.email=email;
    this.phone=phone;
    this.password=password;
 }
 public String getId()
 {
    return id;
 }
 public void setId(String id)
 {
    this.id=id;
 }
 public String getName()
 {
    return name;
 }
 public void setName(String name)
 {
    this.name=name;
 }
 public  String getemail()
 {
    return email;
 }
 public void setemail(String email)
 {
    this.email=email;
 }
 public String getphone()
 {
    return phone;
 }
 public void setphone(String phone)
 {
    this.phone=phone;
 }
 public String getPassward()
 {
    return password;
 }
 public void setPassward(String password)
 {
    this.password=password;
 }

 public void ManageProfile(String NewName,String Newemail,String id)
 {
    setName(NewName);
    setemail(Newemail);
    setId(id);
    System.out.println("Profile Update"+ getName()+getemail()+getId());
}


}
