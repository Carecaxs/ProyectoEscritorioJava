package Config;

// En la carpeta lib hay que agregar el driver
public final class Config {

    // Final hace que no se pueda heredar de esta clase
    /*  REGISTRAR EL CONTROLADOR (DRIVER)
        - Después de instalar el driver debemos registrarlo
        - Se hace con la instrucción Class.forName("tip_driver")
    
     */
    public static String getConnectionString() throws ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        
        return "jdbc:sqlserver://localhost;databaseName=PROYECTO_ESTEFANIA_OSCAR;user=sa;password=sa";
    }
}
