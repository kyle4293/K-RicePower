package com.example.familyalbum.task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.familyalbum.R
import com.example.familyalbum.databinding.ActivityTaskEditBinding
import com.example.familyalbum.databinding.ActivityTaskPlusBinding

class TaskEditActivity : AppCompatActivity() {
    lateinit var binding: ActivityTaskEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskEditBinding.inflate(layoutInflater)
        setContentView(binding.root)


        lateinit var startHour: String
        lateinit var startMin: String
        lateinit var endHour: String
        lateinit var endMin: String
        lateinit var week: String

        // 스피너에 표시할 항목 배열
        var sHours = arrayListOf("09")
        for (i in 10 .. 23){
            if(i<10){
                sHours.add("0${i}")
            }else {
                sHours.add(i.toString())
            }
        }

        // ArrayAdapter를 사용하여 스피너에 항목을 연결
        val sHourAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, sHours)
        sHourAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // 스피너에 어댑터 설정
        binding.startHourSpinner.adapter = sHourAdapter

        // 스피너에서 항목을 선택했을 때 처리
        binding.startHourSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                startHour = selectedItem
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // 아무 항목도 선택되지 않았을 때 처리
            }
        }

        // 스피너에 표시할 항목 배열
        var eHours = arrayListOf("09")
        for (i in 10 .. 23){
            if(i<10){
                eHours.add("0${i}")
            }else {
                eHours.add(i.toString())
            }
        }

        // ArrayAdapter를 사용하여 스피너에 항목을 연결
        val eHourAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, eHours)
        eHourAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // 스피너에 어댑터 설정
        binding.endHourSpinner.adapter = eHourAdapter

        // 스피너에서 항목을 선택했을 때 처리
        binding.endHourSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                endHour = selectedItem
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // 아무 항목도 선택되지 않았을 때 처리
            }
        }

        // 스피너에 표시할 항목 배열
        var sMins = arrayListOf("00","05")
        for (i in 2 .. 11){
            sMins.add("${5*i}")
        }

        // ArrayAdapter를 사용하여 스피너에 항목을 연결
        val sMinAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, sMins)
        sMinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // 스피너에 어댑터 설정
        binding.startMinSpinner.adapter = sMinAdapter

        // 스피너에서 항목을 선택했을 때 처리
        binding.startMinSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                startMin = selectedItem
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // 아무 항목도 선택되지 않았을 때 처리
            }
        }

        // 스피너에 표시할 항목 배열
        var eMins = arrayListOf("00","05")
        for (i in 2 .. 11){
            eMins.add("${5*i}")
        }

        // ArrayAdapter를 사용하여 스피너에 항목을 연결
        val eMinAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, eMins)
        eMinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // 스피너에 어댑터 설정
        binding.endMinSpinner.adapter = eMinAdapter

        // 스피너에서 항목을 선택했을 때 처리
        binding.endMinSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                endMin = selectedItem
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // 아무 항목도 선택되지 않았을 때 처리
            }
        }


        // 스피너에 표시할 항목 배열
        var weeks = arrayListOf("월","화","수","목","금","토","일")

        // ArrayAdapter를 사용하여 스피너에 항목을 연결
        val weekAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, weeks)
        weekAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // 스피너에 어댑터 설정
        binding.weekSpinner.adapter = weekAdapter

        // 스피너에서 항목을 선택했을 때 처리
        binding.weekSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                week = selectedItem
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // 아무 항목도 선택되지 않았을 때 처리
            }
        }


        //*********원래 정보**********
        val startTime = intent.getStringExtra("startTime")
        val endTime = intent.getStringExtra("endTime")
        val title = intent.getStringExtra("title")
        val place = intent.getStringExtra("place")
        var dayOfWeek = intent.getStringExtra("dayOfWeek")


        //*********원래 정보로 기본 설정*******
        var oldstartHour = (startTime!!.toInt() / 100).toString()
        if(oldstartHour.toInt() < 10) {
            oldstartHour = "0${oldstartHour}"
        }
        var oldstartMin = (startTime!!.toInt() % 100).toString()
        if(oldstartMin.toInt() < 10) {
            oldstartMin = "0${oldstartMin}"
        }
        var oldendHour = (endTime!!.toInt() / 100).toString()
        if(oldendHour.toInt() < 10) {
            oldendHour = "0${oldendHour}"
        }
        var oldendMin = (endTime!!.toInt() % 100).toString()
        if(oldendMin.toInt() < 10) {
            oldendMin = "0${oldendMin}"
        }

        val sHposition = sHourAdapter.getPosition(oldstartHour)
        if (sHposition != -1) {
            binding.startHourSpinner.setSelection(sHposition)
        }

        val sMposition = sMinAdapter.getPosition(oldstartMin)
        if (sMposition != -1) {
            binding.startMinSpinner.setSelection(sMposition)
        }

        val eHposition = eHourAdapter.getPosition(oldendHour)
        if (eHposition != -1) {
            binding.endHourSpinner.setSelection(eHposition)
        }

        val eMposition = eMinAdapter.getPosition(oldendMin)
        if (eMposition != -1) {
            binding.endMinSpinner.setSelection(eMposition)
        }

        when(dayOfWeek){
            "mon" -> dayOfWeek ="월"
            "tue" -> dayOfWeek ="화"
            "wed" -> dayOfWeek ="수"
            "thu" -> dayOfWeek ="목"
            "fri" -> dayOfWeek ="금"
            "sat" -> dayOfWeek ="토"
            "sun" -> dayOfWeek ="일"
        }
        val weekposition = weekAdapter.getPosition(dayOfWeek)
        if (weekposition != -1) {
            binding.weekSpinner.setSelection(weekposition)
        }

        if (title != null) {
            binding.inputTaskName.text = Editable.Factory.getInstance().newEditable(title)
        }
        if (place != null) {
            binding.inputTaskPlace.text = Editable.Factory.getInstance().newEditable(place)
        }
        //********기본 설정 끝******


        //*********수정 버튼을 누르면********
        binding.button.setOnClickListener {

            //새로운 정보
            val newtaskName = binding.inputTaskName.text.toString()
            val newtaskPlace = binding.inputTaskPlace.text.toString()
            val newstartTime = "${startHour}${startMin}"
            val newendTime = "${endHour}${endMin}"
            lateinit var newweek: String
            when(week){
                "월" -> newweek = "mon"
                "화" -> newweek = "tue"
                "수" -> newweek = "wed"
                "목" -> newweek = "thu"
                "금" -> newweek = "fri"
                "토" -> newweek = "sat"
                "일" -> newweek = "sun"
            }

            // 요기서 원래 정보로 DB찾고 그 DB에 새로운 정보로 update

            
        }
    }
}