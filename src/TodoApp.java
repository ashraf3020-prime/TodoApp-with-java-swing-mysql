import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TodoApp extends JFrame {

    JTextField taskField;
    JTextField deleteField;

    JTextArea area;

    JButton addButton;
    JButton deleteButton;

    TaskDAO dao = new TaskDAO();

    public TodoApp() {

        setTitle("Todo App");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel top = new JPanel();

        taskField = new JTextField(20);
        addButton = new JButton("Add Task");

        deleteField = new JTextField(5);
        deleteButton = new JButton("Delete");

        top.add(new JLabel("Task"));
        top.add(taskField);
        top.add(addButton);

        top.add(new JLabel("Delete ID"));
        top.add(deleteField);
        top.add(deleteButton);

        add(top, BorderLayout.NORTH);

        area = new JTextArea();
        area.setEditable(false);

        add(new JScrollPane(area), BorderLayout.CENTER);

        addButton.addActionListener(e -> addTask());

        deleteButton.addActionListener(e -> deleteTask());

        loadTasks();

        setLocationRelativeTo(null);

        setVisible(true);
    }

    private void addTask() {

        String task = taskField.getText().trim();

        if(task.isEmpty()){

            JOptionPane.showMessageDialog(this,
                    "Enter a task!");

            return;
        }

        dao.addTask(task);

        taskField.setText("");

        loadTasks();

    }

    private void deleteTask() {

        try{

            int id = Integer.parseInt(deleteField.getText());

            dao.deleteTask(id);

            deleteField.setText("");

            loadTasks();

        }

        catch (Exception e){

            JOptionPane.showMessageDialog(this,
                    "Invalid ID");

        }

    }

    private void loadTasks(){

        area.setText("");

        ArrayList<Task> list = dao.getAllTasks();

        for(Task t : list){

            area.append(

                    t.getId()
                            + " : "
                            + t.getTask()
                            + "\n"

            );

        }

    }

}