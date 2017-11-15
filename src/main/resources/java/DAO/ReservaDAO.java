package DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Conexion;
import modelo.Reserva;
import modelo.SinConexionException;
import modelo.Sucursal;

public class ReservaDAO {
	
	//listar todas las reservas del sistema // Todos los usuarios
	
	public static List<Reserva> obtenerReservas() throws SQLException, SinConexionException{
		
		List<Reserva> reservas = new ArrayList<Reserva>();
		
		PreparedStatement st = Conexion.getInstancia().prepareStatement(""
				+ "select * from reserva");
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			
			Reserva reserva = new Reserva();
			reserva.setIdReserva(rs.getInt("idreserva"));
			reserva.setFechaReserva(rs.getDate("fechaReserva"));
			reserva.setFechaActual(rs.getDate("fechaAcutal"));
			reserva.setServicio(rs.getString("servicio"));
			reservas.add(reserva);
		}
		
		return reservas;
		
	}
	
	public static void agregarReserva(Reserva reserva, Sucursal sucursal) throws SQLException, SinConexionException {
		
		PreparedStatement st = Conexion.getInstancia().prepareStatement(""
				+ "insert into reserva values (?,?,?,?);");
		
		st.setInt(1, sucursal.getIdSucursal());
		st.setDate(2, (Date) reserva.getFechaReserva());
		st.setDate(3, (Date) reserva.getFechaActual());
		st.setString(4, reserva.getServicio());
		
		st.executeUpdate();
		
	}
	
	
	
}
