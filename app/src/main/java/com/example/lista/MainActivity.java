package com.example.lista;

import static java.lang.String.format;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MainActivity extends Activity {

    public Map<String, String> capitalByState () {

        String[] data = {
                "Acre",
                "Rio Branco",
                "Alagoas",
                "Maceió",
                "Amapá",
                "Macapá",
                "Amazonas",
                "Manaus",
                "Bahia",
                "Salvador",
                "Ceará",
                "Fortaleza",
                "Espírito Santo",
                "Vitória",
                "Goiás",
                "Goiânia",
                "Maranhão",
                "São Luís",
                "Mato Grosso",
                "Cuiabá",
                "Mato Grosso do Sul",
                "Campo Grande",
                "Minas Gerais",
                "Belo Horizonte",
                "Pará",
                "Belém",
                "Paraíba",
                "João Pessoa",
                "Paraná",
                "Curitiba",
                "Pernambuco",
                "Recife",
                "Piauí",
                "Teresina",
                "Rio de Janeiro",
                "Rio de Janeiro",
                "Rio Grande do Norte",
                "Natal",
                "Rio Grande do Sul",
                "Porto Alegre",
                "Rondônia",
                "Porto Velho",
                "Roraima",
                "Boa Vista",
                "Santa Catarina",
                "Florianópolis",
                "São Paulo",
                "São Paulo",
                "Sergipe",
                "Aracaju",
                "Tocantins",
                "Palmas",
                "Distrito Federal",
                "Brasília"
        };

        HashMap<String, String> map = new HashMap<>();

        for (int i = 0; i < data.length; i += 2) {
            map.put(data[i], data[i+1]);
        }

        return map;
    }

    private String randomState(Map<String, String> map) {

        int randomIdx = getRandomNumber(0, 26);

        Set<String> states = map.keySet();
        String[] statesArr = states.toArray(new String[0]);

        return statesArr[randomIdx];
    }

    TextView stateLabel;
    EditText inputUser;
    Button checkAnswerBtn;
    Button nextRoundBtn;
    TextView feedback;
    TextView scoreLabel;
    int round = 1;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);

        stateLabel= (TextView) findViewById(R.id.textView_state_name);
        inputUser=(EditText) findViewById(R.id.editText_input_user);
        checkAnswerBtn=(Button) findViewById(R.id.check_answer_btn);
        nextRoundBtn=(Button) findViewById(R.id.next_round_btn);
        feedback = (TextView) findViewById(R.id.textView_feedback);
        scoreLabel = (TextView) findViewById(R.id.textView_score);

        Map<String, String> map = capitalByState();

        stateLabel.setText(randomState(map));

        checkAnswerBtn.setOnClickListener(v -> {

            String capital = map.get(stateLabel.getText().toString());
            String inputTxt = inputUser.getText().toString().trim();

            if (capital.equalsIgnoreCase(inputTxt)) {
                feedback.setText("Resposta correta" );
                score += 10;
                scoreLabel.setText(format("Pontuação: %d", score));
            }
            else {
                feedback.setText("Resposta incorreta");
            }

        });

        nextRoundBtn.setOnClickListener(v -> {
            if (round == 5) {
                nextRoundBtn.setEnabled(false);
                checkAnswerBtn.setEnabled(false);
            }
            else {
                map.remove(stateLabel.getText().toString());
                feedback.setText("");
                inputUser.getText().clear();

                round++;
                stateLabel.setText(randomState(map));
                if (round == 5) {
                    nextRoundBtn.setEnabled(false);
                }
            }
        });

    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

}