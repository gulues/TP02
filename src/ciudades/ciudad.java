package ciudades;

public class ciudad {
	private String _nombre;
	private String _localidad;
	private String provincia;
	private int latitud;
	private int longitud;
	private int cantidadHabitantes;
	
	public ciudad(String _nombre, String _localidad, String provincia,
			int latitud, int longitud, int cantidadHabitantes) {
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

	public int getLatitud() {
		return latitud;
	}

	public void setLatitud(int latitud) {
		this.latitud = latitud;
	}

	public int getLongitud() {
		return longitud;
	}

	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}

	public int getCantidadHabitantes() {
		return cantidadHabitantes;
	}

	public void setCantidadHabitantes(int cantidadHabitantes) {
		this.cantidadHabitantes = cantidadHabitantes;
	}
}
