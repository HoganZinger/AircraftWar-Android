package edu.hitsz;

import android.database.MatrixCursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import edu.hitsz.dao.Player;
import edu.hitsz.dao.PlayerDAOImpl;

public class RankingListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rankinglist_adapter_activity_layout);

        String[] simpleCursor = new String[] {"_id", "name", "score", "time"};
        MatrixCursor cursor = new MatrixCursor(simpleCursor);

        List<Player> playerList = PlayerDAOImpl.getPlayerList();
        for (Player curPlayer : playerList){
            Object playerObject = new Object[] {curPlayer.getPlayerID(), curPlayer.getPlayerName(), curPlayer.getScore(), curPlayer.getDateTime()};
            cursor.addRow((Object[]) playerObject);
        }


        ListView listView = findViewById(R.id.list);
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.single_player_adapter_item,
                cursor,
                simpleCursor,
                new int[] {R.id.simple_id, R.id.simple_name, R.id.simple_score, R.id.simple_time},
                0);
        // 设置分割线
        listView.setDivider(new ColorDrawable(Color.BLUE));
        // 设置分割线的宽度
        listView.setDividerHeight(5);
        listView.setAdapter(adapter);
    }
}
