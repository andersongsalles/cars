package com.devapps.cars.domain

import android.content.Context
import com.devapps.cars.R
import com.devapps.cars.R.string.tipo
import com.devapps.cars.extensions.fromJson
import com.devapps.cars.utils.HttpHelper

object CarroService {
    private val BASE_URL = "http://livrowebservices.com.br/rest/carros"

    // Busca os carros por tipo (clássicos, esportivos ou luxo)
    fun getCarros(tipo: TipoCarro): List<Carro> {
        val url = "$BASE_URL/tipo/${tipo.name}"
        val json = HttpHelper.get(url)
        val carros = fromJson<List<Carro>>(json)
        return carros
    }

    // Retorna o arquivo que temos que ler para o tipo informado
    fun getArquivoRaw(tipo: TipoCarro) = when (tipo) {
        TipoCarro.classicos -> R.raw.carros_classicos
        TipoCarro.esportivos -> R.raw.carros_esportivos
        else -> R.raw.carros_luxo
    }
}