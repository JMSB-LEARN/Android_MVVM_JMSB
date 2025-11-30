package com.example.mvvm_ejercicio_jmsb;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.mvvm_ejercicio_jmsb.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private NavController navController;
    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((binding = ActivityMainBinding.inflate(getLayoutInflater())).getRoot());
// 1. Configuramos la toolbar
        setSupportActionBar(binding.toolbar);
// 2. Obtenemos el NavController
        navController = ((NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment)).getNavController();
// 3. Configuramos la AppBar con los destinos principales (bottom menu)
// Los fragments que pongamos aquí se consideran top-level destinations
// Esto quiere decir en ellas no hay botón de back porque no se llegó desde otra,
// sino que se accede directamente desde el menú
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.beesFragment
        ).build();

// 4. Vinculamos la Toolbar con el NavController indicando la configuración correcta
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

// 5. Sincronizamos BottomNavigationView con el NavController

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // This single line handles the navigation automatically if IDs match
        return NavigationUI.onNavDestinationSelected(item, navController)
                || super.onOptionsItemSelected(item);
    }

    // Método que se ejecuta cuando el usuario pulsa el botón de “navegación superior” de la barra de la app, es decir:
// el icono de flecha atrás, o el icono de menú hamburguesa (si tienes un DrawerLayout).
    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

}