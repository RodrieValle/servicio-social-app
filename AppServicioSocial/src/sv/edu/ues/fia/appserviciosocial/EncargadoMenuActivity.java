package sv.edu.ues.fia.appserviciosocial;

import java.util.ArrayList;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;

public class EncargadoMenuActivity extends TabActivity {
	private String[] titulos;
	private DrawerLayout NavDrawerLayout;
	private ListView NavList;
	private ArrayList<Item_objct> NavItms;
	private TypedArray NavIcons;
	private ActionBarDrawerToggle mDrawerToggle;
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	NavigationAdapter NavAdapter;
	int tipoUsuario;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_encargado_menu);

		Bundle b = getIntent().getExtras();
		tipoUsuario = b.getInt("tipoUsuario");

		Intent intent1 = new Intent(this, EncargadoInsertarActivity.class);
		Intent intent2 = new Intent(this, EncargadoActualizarActivity.class);
		Intent intent3 = new Intent(this, EncargadoConsultarActivity.class);
		Intent intent4 = new Intent(this, EncargadoEliminarActivity.class);

		// TABS
		TabHost tabHost = getTabHost();
		if(tipoUsuario==1)
		{
		tabHost.addTab(tabHost.newTabSpec("Crear")
				.setIndicator("", getResources().getDrawable(R.drawable.nuevo))
				.setContent(intent1));
		tabHost.addTab(tabHost
				.newTabSpec("Consultar")
				.setIndicator("",
						getResources().getDrawable(R.drawable.consultar))
				.setContent(intent3));
		tabHost.addTab(tabHost
				.newTabSpec("Actualizar")
				.setIndicator("",
						getResources().getDrawable(R.drawable.actualizar))
				.setContent(intent2));
		tabHost.addTab(tabHost
				.newTabSpec("Eliminar")
				.setIndicator("", getResources().getDrawable(R.drawable.delete))
				.setContent(intent4));
		}
		else 
			if(tipoUsuario==2)
			{
				tabHost.addTab(tabHost
						.newTabSpec("Consultar")
						.setIndicator("",
								getResources().getDrawable(R.drawable.consultar))
						.setContent(intent3));
			}
		// ///////////////////////////////////////////////////////////////////////////////////////////////

		// Creacion del DRAWER
		this.NavDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		// Lista
		NavList = (ListView) findViewById(R.id.lista);
		// Declaramos el header el cual sera el layout de header.xml
		View header = getLayoutInflater().inflate(R.layout.header, null);
		// Establecemos header
		NavList.addHeaderView(header);
		// Tomamos listado de imgs desde drawable
		NavIcons = getResources().obtainTypedArray(R.array.navigation_iconos);
		// Tomamos listado de titulos desde el string-array de los recursos
		// @string/nav_options
		titulos = getResources().getStringArray(R.array.nav_options);
		// Listado de titulos de barra de navegacion
		NavItms = new ArrayList<Item_objct>();
		
		//Bundle b = getIntent().getExtras();
		//tipoUsuario = b.getInt("tipoUsuario");
		
		if (tipoUsuario == 1) {
			// Agregamos objetos Item_objct al array
			NavItms.add(new Item_objct(titulos[0], NavIcons
					.getResourceId(0, -1)));
			NavItms.add(new Item_objct(titulos[1], NavIcons
					.getResourceId(1, -1)));
			NavItms.add(new Item_objct(titulos[2], NavIcons
					.getResourceId(2, -1)));
			NavItms.add(new Item_objct(titulos[3], NavIcons
					.getResourceId(3, -1)));
			NavItms.add(new Item_objct(titulos[4], NavIcons
					.getResourceId(4, -1)));
			NavItms.add(new Item_objct(titulos[5], NavIcons
					.getResourceId(5, -1)));
			NavItms.add(new Item_objct(titulos[6], NavIcons
					.getResourceId(6, -1)));
			NavItms.add(new Item_objct(titulos[7], NavIcons
					.getResourceId(7, -1)));
			NavItms.add(new Item_objct(titulos[8], NavIcons
					.getResourceId(8, -1)));
			NavItms.add(new Item_objct(titulos[9], NavIcons
					.getResourceId(9, -1)));
		} else if (tipoUsuario == 2) {
			NavItms.add(new Item_objct(titulos[0], NavIcons
					.getResourceId(0, -1)));
			NavItms.add(new Item_objct(titulos[4], NavIcons
					.getResourceId(0, -1)));
			NavItms.add(new Item_objct(titulos[5], NavIcons
					.getResourceId(5, -1)));
			NavItms.add(new Item_objct(titulos[6], NavIcons
					.getResourceId(6, -1)));
			NavItms.add(new Item_objct(titulos[8], NavIcons
					.getResourceId(9, -1)));
		}
		// Declaramos y seteamos nuestrp adaptador al cual le pasamos el array
		// con los titulos
		NavAdapter = new NavigationAdapter(this, NavItms);
		NavList.setAdapter(NavAdapter);
		// Siempre vamos a mostrar el mismo titulo

		// Siempre vamos a mostrar el mismo titulo
		mTitle = mDrawerTitle = getTitle();

		// Declaramos el mDrawerToggle y las imgs a utilizar
		mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
		NavDrawerLayout, /* DrawerLayout object */
		R.drawable.ic_drawer, /* Icono de navegacion */
		R.string.title_activity_menu_alumno, /* "open drawer" description */
		R.string.hello_world /* "close drawer" description */
		) {

			/** Called when a drawer has settled in a completely closed state. */
			public void onDrawerClosed(View view) {
				setTitle(mTitle);
			}

			/** Called when a drawer has settled in a completely open state. */
			public void onDrawerOpened(View drawerView) {
				setTitle(R.string.title_activity_menu_tablas);
			}
		};
		//
		// Establecemos que mDrawerToggle declarado anteriormente sea el
		// DrawerListener
		// mDrawerToggle.setDrawerIndicatorEnabled(true);
		NavDrawerLayout.setDrawerListener(mDrawerToggle);
		// Establecemos que el ActionBar muestre el Boton Home
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		// Establecemos la accion al clickear sobre cualquier item del menu.
		// De la misma forma que hariamos en una app comun con un listview.
		NavList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long id) {
				if (position != 0) {
					NavDrawerLayout.closeDrawer(NavList);
					abrirActivity(position);
				}
			}
		});

	}
	
	
	
	private void abrirActivity(int posicion) {
		Intent i = null;
		switch (tipoUsuario) 
		{
		case 1:
			switch (posicion) {
			case 1:
				i = new Intent(this, AlumnoMenuActivity.class);
				break;
			case 2:
				i = new Intent(this, AsignacionProyectoMenuActivity.class);
				break;
			case 3:
				i = new Intent(this, BitacoraMenuActivity.class);
				break;
			case 4:
				i = new Intent(this, CargoMenuActivity.class);
				break;
			case 5:
				i = new Intent(this, EncargadoMenuActivity.class);
				break;
			case 6:
				i = new Intent(this, InstitucionMenuActivity.class);
				break;
			case 7:
				i = new Intent(this, ProyectoMenuActivity.class);
				break;
			case 8:
				i = new Intent(this, SolicitanteMenuActivity.class);
				break;
			case 9:
				i = new Intent(this, TipoProyectoMenuActivity.class);
				break;
			case 10:
				i = new Intent(this, TipoTrabajoActivity.class);
				break;
			}
			break;
		case 2:
			switch (posicion) {
			case 1:
				i = new Intent(this, AlumnoMenuActivity.class);
				break;
			case 2:
				i = new Intent(this, EncargadoMenuActivity.class);
				break;
			case 3:
				i = new Intent(this, InstitucionMenuActivity.class);
				break;
			case 4:
				i = new Intent(this, ProyectoMenuActivity.class);
				break;
			case 5:
				i = new Intent(this, TipoProyectoMenuActivity.class);
				break;

			}
			break;
		}
		i.putExtra("tipoUsuario", tipoUsuario);
		startActivity(i);
		overridePendingTransition(R.anim.left_in, R.anim.left_out);
	}
	
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// Called by the system when the device configuration changes while your
		// activity is running
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Pass the event to ActionBarDrawerToggle, if it returns
		// true, then it has handled the app icon touch event
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle your other action bar items...
		return super.onOptionsItemSelected(item);
	}

	// ///FIN DE DRAWER
	// /////////////////////////////////////////////////////////////////////////////////////////
}
