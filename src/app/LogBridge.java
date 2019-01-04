package app;

import javafx.application.Platform;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import model.Post5ch;
import model.Thread5ch;

import java.util.ArrayList;

public class LogBridge implements LogParser {

    private TableView<Thread5ch> crawlingList;
    private TextArea dbLog;

    @Override
    public void loadThreadList(ArrayList<Thread5ch> arrayList) {
        //do nothing
    }

    @Override
    public void addThread(Thread5ch thread5ch) {
        Platform.runLater(() -> crawlingList.getItems().add(thread5ch));

    }

    @Override
    public void removeThread(Thread5ch thread5ch) {
        Platform.runLater(() -> crawlingList.getItems().remove(thread5ch)
        );


    }

    @Override
    public void insertedPost(Post5ch post) {
        StringBuilder sb = new StringBuilder();

        sb.append("Postを挿入したよ");
        sb.append("(");
        sb.append(post.getNumber());
        sb.append(": ");
        sb.append(post.getName());
        sb.append(" ");
        sb.append(post.getMail());
        sb.append(" ");
        sb.append(post.getTime());
        if(post.getUid() != null) {
            sb.append(" ID:");
            sb.append(post.getUid());
        }
        sb.append(" '");
        sb.append(post.getComment());
        sb.append("')");

        sb.append("\n");

        //dbLog.appendText(sb.toString());
        Platform.runLater(
                () -> dbLog.appendText(sb.toString())
        );
    }

    @Override
    public void insertedThread(Thread5ch th) {
        StringBuilder sb = new StringBuilder();

        sb.append("Threadを挿入したよ(");
        sb.append(th.getKey());
        sb.append(": ");
        sb.append(th.getTitle());
        sb.append("[");
        sb.append(th.getEnd());
        sb.append("]");
        sb.append(")");

        sb.append("\n");

        //dbLog.appendText(sb.toString());
        Platform.runLater(
                () -> dbLog.appendText(sb.toString())
        );
    }

    @Override
    public void updatedThread(Thread5ch th) {
        StringBuilder sb = new StringBuilder();

        sb.append("Threadを更新したよ(");
        sb.append(th.getKey());
        sb.append(": ");
        sb.append(th.getTitle());
        sb.append("[");
        sb.append(th.getEnd());
        sb.append("]");
        sb.append(")");

        sb.append("\n");

        //dbLog.appendText(sb.toString());
        Platform.runLater(
                () -> dbLog.appendText(sb.toString())
        );
    }

    @Override
    public void printErr(Exception e) {
        System.out.println("Error: " + e.getMessage());
        e.printStackTrace(); //いらない
    }

    @Override
    public void printErr(String s) {
        Platform.runLater(() -> dbLog.appendText("Error: " + s + "\n"));
    }

    public void setCrawlingList(TableView<Thread5ch> crawlingList) {
        this.crawlingList = crawlingList;
    }

    public void setDbLog(TextArea dbLog) {
        this.dbLog = dbLog;
    }
}
