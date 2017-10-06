package ciudades;

public class ciudad {
	private String _nombre;
	private String _localidad;
	private String provincia;
	private double latitud;
	private double longitud;
	private int cantidadHabitantes;
	
	public ciudad(String _nombre, String _localidad, String provincia,
			double latitud, double longitud, int cantidadHabitantes) {
		this._nombre = _nombre;
		this._localidad = _localidad;
		this.provincia = provincia;
		this.latitud = latitud;
		this.longitud = longitud;
		this.cantidadHabitantes = cantidadHabitantes;
	}

	@Override
	public String toString() {
		return "ciudad [_nombre=" + _nombre + ", _localidad=" + _localidad
				+ ", provincia=" + provincia + ", latitud=" + latitud
				+ ", longitud=" + longitud + ", cantidadHabitantes="
				+ cantidadHabitantes + "]";
	}

	public String get_nombre() {
		return _nombre;
	}

	public void set_nombre(String _nombre) {
		this._nombre = _nombre;
	}

	public String get_localidad() {
		return _localidad;
	}

	public void set_localidad(String _localidad) {
		this._localidad = _localidad;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public int getCantidadHabitantes() {
		return cantidadHabitantes;
	}

	public void setCantidadHabitantes(int cantidadHabitantes) {
		this.cantidadHabitantes = cantidadHabitantes;
	}
	public static double distancia(double lat1, double lon1, double lat2, double lon2) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
				+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		dist = dist * 1.609344;
		return (dist);
	}

	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	/* :: This function converts decimal degrees to radians : */
	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	/* :: This function converts radians to decimal degrees : */
	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}

	

	

}
