package tranlam.googlefirebase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tranlam.googlefirebase.model.JournalEntry;
import tranlam.googlefirebase.model.SampleData;

public class DiaryListActivity extends AppCompatActivity {

    @BindView(R.id.journal_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.empty_text)
    TextView mEmptyText;
    private DatabaseReference mDatabase;
    private DatabaseReference journalCloudEndPoint;
    private FirebaseRecyclerAdapter<JournalEntry, JournalViewHolder> mJournalFirebaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_list);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        journalCloudEndPoint = mDatabase.child("/users/" + "journalentries");

        List<JournalEntry> sampleJournalEntries = SampleData.getSampleJournalEntries();
        for (JournalEntry journalEntry : sampleJournalEntries) {
            String key = journalCloudEndPoint.push().getKey();
            journalEntry.setJournalId(key);
            journalCloudEndPoint.child(key).setValue(journalEntry);
        }

        ButterKnife.bind(this, this);


        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mJournalFirebaseAdapter = new FirebaseRecyclerAdapter<JournalEntry, JournalViewHolder>(
                JournalEntry.class,
                R.layout.journal_custom_row,
                JournalViewHolder.class,
                journalCloudEndPoint) {

            @Override
            protected JournalEntry parseSnapshot(DataSnapshot snapshot) {
                JournalEntry note = super.parseSnapshot(snapshot);
                if (note != null) {
                    note.setJournalId(snapshot.getKey());
                }
                return note;
            }

            @Override
            protected void populateViewHolder(JournalViewHolder holder, final JournalEntry journalEntry, int position) {
                holder.title.setText(journalEntry.getTitle());

                holder.journalDate.setText(getDueDate(journalEntry.getDateModified()));
                holder.delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!TextUtils.isEmpty(journalEntry.getJournalId())) {
                            journalCloudEndPoint.child(journalEntry.getJournalId()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    if (mJournalFirebaseAdapter.getItemCount() < 1) {
                                        showEmptyText();
                                    }
                                }
                            });
                        }
                    }
                });
                String firstLetter = journalEntry.getTitle().substring(0, 1);
                ColorGenerator generator = ColorGenerator.MATERIAL;
                int color = generator.getRandomColor();
                TextDrawable drawable = TextDrawable.builder()
                        .buildRound(firstLetter, color);
                holder.journalIcon.setImageDrawable(drawable);
            }
        };
        mRecyclerView.setAdapter(mJournalFirebaseAdapter);
    }
    private static String getDueDate(long date) {
        String displayDate = new SimpleDateFormat("MMM dd, yyyy").format(new Date(date));
        return displayDate;
    }
    public void showEmptyText() {
        mRecyclerView.setVisibility(View.GONE);
        mEmptyText.setVisibility(View.VISIBLE);
    }
    public void hideEmptyText() {
        mRecyclerView.setVisibility(View.VISIBLE);
        mEmptyText.setVisibility(View.GONE);
    }

}
