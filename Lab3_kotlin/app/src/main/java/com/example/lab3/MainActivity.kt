package com.example.lab3

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var edName: EditText
    private lateinit var tvText: TextView
    private lateinit var tvName: TextView
    private lateinit var tvWinner: TextView
    private lateinit var tvMmora: TextView
    private lateinit var tvCmora: TextView
    private lateinit var radioGroup: RadioGroup
    private lateinit var button: Button
    private lateinit var resetButton: Button // 新增重置按鈕

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        edName = findViewById(R.id.ed_name)
        tvText = findViewById(R.id.tv_text)
        tvName = findViewById(R.id.tv_name)
        tvWinner = findViewById(R.id.tv_winner)
        tvMmora = findViewById(R.id.tv_mmora)
        tvCmora = findViewById(R.id.tv_cmora)
        radioGroup = findViewById(R.id.rG) // 使用 RadioGroup
        button = findViewById(R.id.button)
        resetButton = findViewById(R.id.reset_button) // 初始化重置按鈕

        button.setOnClickListener {
            if (edName.text.isEmpty()) {
                tvText.text = "請輸入玩家姓名"
            } else {
                tvName.text = "名字\n${edName.text}"
                tvMmora.text = when (radioGroup.checkedRadioButtonId) {
                    R.id.scissor -> "我方出拳\n剪刀"
                    R.id.stone -> "我方出拳\n石頭"
                    else -> "我方出拳\n布"
                }
                val computerRandom = (0..2).random()

                tvCmora.text = when (computerRandom) {
                    0 -> "電腦出拳\n剪刀"
                    1 -> "電腦出拳\n石頭"
                    else -> "電腦出拳\n布"
                }

                when {
                    (radioGroup.checkedRadioButtonId == R.id.scissor && computerRandom == 2) ||
                            (radioGroup.checkedRadioButtonId == R.id.stone && computerRandom == 0) ||
                            (radioGroup.checkedRadioButtonId == R.id.paper && computerRandom == 1) -> {
                        tvWinner.text = "勝利者\n${edName.text}"
                        tvText.text = "恭喜您獲勝了!!!"
                    }
                    (radioGroup.checkedRadioButtonId == R.id.scissor && computerRandom == 1) ||
                            (radioGroup.checkedRadioButtonId == R.id.stone && computerRandom == 2) ||
                            (radioGroup.checkedRadioButtonId == R.id.paper && computerRandom == 0) -> {
                        tvWinner.text = "勝利者\n電腦"
                        tvText.text = "可惜，電腦獲勝了!"
                    }
                    else -> {
                        tvWinner.text = "勝利者\n平手"
                        tvText.text = "平局，請再試一次!"
                    }
                }
            }
        }

        resetButton.setOnClickListener {
            edName.text.clear() // 清除玩家姓名輸入框
            tvText.text = "輸入姓名以開始遊戲" // 恢復提示文字
            radioGroup.clearCheck() // 清除 RadioGroup 中的選項
            tvName.text = "名字" // 恢復名字提示
            tvWinner.text = "勝利者" // 恢復勝利者提示
            tvMmora.text = "我方出拳" // 恢復我方出拳提示
            tvCmora.text = "電腦出拳" // 恢復電腦出拳提示
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
