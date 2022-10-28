package com.example.lugares.ui.lugar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.lugares.R
import com.example.lugares.databinding.FragmentAddLugarBinding
import com.example.lugares.databinding.FragmentLugarBinding
import com.example.lugares.model.Lugar
import com.example.lugares.viewmodel.LugarViewModel


class AddLugarFragment : Fragment() {

    private var _binding: FragmentAddLugarBinding? = null
    private val binding get() = _binding!!
    private lateinit var lugarViewModel : LugarViewModel //Agregar variable
    /*Mediante lateinit lo que conseguimos es una inicializacion tardia,
    indicando al compilador que dicha inicializacion se realizara mas adelante
    en el codigo. Si intentamos acceder a alguna variable antes de su
    inicializacion obtendremos un error del tipo Caused by*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        lugarViewModel =
            ViewModelProvider(this)[LugarViewModel::class.java]
        _binding = FragmentAddLugarBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun addLugar() {
        val nombre = binding.etNombre.text.toString()
        val correo = binding.etCorreo.text.toString()
        val telefono = binding.etTelefono.text.toString()
        val web = binding.etWeb.text.toString()
        if (nombre.isNotEmpty()) { //Si pudo crear un lugar
            val lugar = Lugar(
                0, nombre, correo, telefono, web, 0.0,
                0.0, 0.0, "", ""
            )

            lugarViewModel.addLugar(lugar)
            Toast.makeText(requireContext(), "Lugar Agregado", Toast.LENGTH_SHORT).show()
            // Para devolvernos al fragment addLugar

        } else { //Mensaje de error...
            Toast.makeText(requireContext(), "Faltan Datos", Toast.LENGTH_SHORT).show()
        }
        findNavController().navigate(R.id.action_addLugarFragment_to_nav_lugar)
    }

}