package co.edu.unipiloto.app_urbanismo_tactico.modelo;

public class Usuario {
    private int Id, isAdmin;
    private String nombre, usuario, cedula, email, password, confirmpassword;

    public Usuario() {

    }

    public Usuario(String nombre, String usuario, String email,String cedula, String password, String confirmpassword,int userType) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.cedula= cedula;
        this.email = email;
        this.password = password;
        this.confirmpassword = confirmpassword;
        this.isAdmin = userType;
    }

    public boolean isNull (){
        if (nombre.equals("")&&usuario.equals("")&&cedula.equals("")&&email.equals("")&&password.equals("")&&confirmpassword.equals("")){
            return false;
        }else {
            return true;
        }
    }
    public int getIsAdmin() { return isAdmin;}

    public boolean setIsAdmin(int isAdmin){
        if(isAdmin == 1 || isAdmin == 0){
            this.isAdmin = isAdmin;
            return true;
        }else
            return false;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "Id=" + Id +
                ", nombre='" + nombre + '\'' +
                ", usuario='" + usuario + '\'' +
                ", cedula='" + cedula + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirmpassword='" + confirmpassword + '\'' +
                ",isAdmin =" + isAdmin + '\''+
                '}';
    }
}
