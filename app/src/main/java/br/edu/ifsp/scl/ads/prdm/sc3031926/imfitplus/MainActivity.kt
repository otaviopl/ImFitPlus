package br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus.databinding.MainActivityBinding
import br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus.ui.theme.ImFitPlusTheme

class MainActivity : AppCompatActivity() {
    private lateinit var binding : MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}