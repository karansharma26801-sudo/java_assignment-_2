import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Student {

    int id;
    String name;
    int age;
    String courses;

    Student(int id, String name, int age, String courses) {

        this.id = id;
        this.name = name;
        this.age = age;
        this.courses = courses;
    }
}

public class StudentManagementSystemAWT extends Frame
        implements ActionListener {

    // Labels
    Label idLabel, nameLabel, ageLabel, courseLabel, searchLabel;

    // TextFields
    TextField idField, nameField, ageField, courseField, searchField;

    // Buttons
    Button addBtn, updateBtn, deleteBtn, clearBtn, searchBtn;

    // Display Area
    TextArea displayArea;

    // Student List
    ArrayList<Student> students = new ArrayList<>();

    StudentManagementSystemAWT() {

        setTitle("Student Management System");
        setSize(800, 600);
        setLayout(new BorderLayout(10, 10));

        // ================= TOP PANEL =================
        Panel topPanel = new Panel();

        topPanel.setLayout(new GridLayout(5, 2, 10, 10));

        idLabel = new Label("Student ID:");
        nameLabel = new Label("Name:");
        ageLabel = new Label("Age:");
        courseLabel = new Label("Courses:");
        searchLabel = new Label("Search ID:");

        idField = new TextField();
        nameField = new TextField();
        ageField = new TextField();
        courseField = new TextField();
        searchField = new TextField();

        topPanel.add(idLabel);
        topPanel.add(idField);

        topPanel.add(nameLabel);
        topPanel.add(nameField);

        topPanel.add(ageLabel);
        topPanel.add(ageField);

        topPanel.add(courseLabel);
        topPanel.add(courseField);

        topPanel.add(searchLabel);
        topPanel.add(searchField);

        add(topPanel, BorderLayout.NORTH);

        // ================= MIDDLE PANEL =================
        Panel buttonPanel = new Panel();

        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        addBtn = new Button("Add");
        updateBtn = new Button("Update");
        deleteBtn = new Button("Delete");
        clearBtn = new Button("Clear");
        searchBtn = new Button("Search");

        buttonPanel.add(addBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(clearBtn);
        buttonPanel.add(searchBtn);

        add(buttonPanel, BorderLayout.CENTER);

        // ================= BOTTOM PANEL =================
        displayArea = new TextArea();

        displayArea.setEditable(false);

        add(displayArea, BorderLayout.SOUTH);

        // ================= EVENT HANDLING =================
        addBtn.addActionListener(this);
        updateBtn.addActionListener(this);
        deleteBtn.addActionListener(this);
        clearBtn.addActionListener(this);
        searchBtn.addActionListener(this);

        // Window Close
        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {

                dispose();
            }
        });

        setVisible(true);
    }

    // ================= ACTION EVENTS =================
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addBtn) {

            addStudent();
        }

        else if (e.getSource() == updateBtn) {

            updateStudent();
        }

        else if (e.getSource() == deleteBtn) {

            deleteStudent();
        }

        else if (e.getSource() == clearBtn) {

            clearFields();
        }

        else if (e.getSource() == searchBtn) {

            searchStudent();
        }
    }

    // ================= ADD =================
    void addStudent() {

        try {

            if (idField.getText().isEmpty() ||
                nameField.getText().isEmpty() ||
                ageField.getText().isEmpty() ||
                courseField.getText().isEmpty()) {

                displayArea.setText("Please fill all fields!");

                return;
            }

            int id = Integer.parseInt(idField.getText());

            String name = nameField.getText();

            int age = Integer.parseInt(ageField.getText());

            String courses = courseField.getText();

            Student s = new Student(id, name, age, courses);

            students.add(s);

            displayStudents();

            clearFields();

        } catch (NumberFormatException ex) {

            displayArea.setText("Enter valid numeric values!");
        }
    }

    // ================= UPDATE =================
    void updateStudent() {

        try {

            int id = Integer.parseInt(idField.getText());

            boolean found = false;

            for (Student s : students) {

                if (s.id == id) {

                    s.name = nameField.getText();
                    s.age = Integer.parseInt(ageField.getText());
                    s.courses = courseField.getText();

                    found = true;

                    break;
                }
            }

            if (found) {

                displayArea.setText("Student Updated Successfully!");

            } else {

                displayArea.setText("Student ID not found!");
            }

            displayStudents();

        } catch (NumberFormatException ex) {

            displayArea.setText("Invalid Input!");
        }
    }

    // ================= DELETE =================
    void deleteStudent() {

        try {

            int id = Integer.parseInt(idField.getText());

            boolean removed = false;

            for (int i = 0; i < students.size(); i++) {

                if (students.get(i).id == id) {

                    students.remove(i);

                    removed = true;

                    break;
                }
            }

            if (removed) {

                displayArea.setText("Student Deleted Successfully!");

            } else {

                displayArea.setText("Student ID not found!");
            }

            displayStudents();

        } catch (NumberFormatException ex) {

            displayArea.setText("Invalid Input!");
        }
    }

    // ================= SEARCH =================
    void searchStudent() {

        try {

            int searchId = Integer.parseInt(searchField.getText());

            boolean found = false;

            for (Student s : students) {

                if (s.id == searchId) {

                    displayArea.setText(
                            "ID: " + s.id + "\n" +
                            "Name: " + s.name + "\n" +
                            "Age: " + s.age + "\n" +
                            "Courses: " + s.courses
                    );

                    found = true;

                    break;
                }
            }

            if (!found) {

                displayArea.setText("Student Not Found!");
            }

        } catch (NumberFormatException ex) {

            displayArea.setText("Enter valid ID!");
        }
    }

    // ================= DISPLAY =================
    void displayStudents() {

        displayArea.setText("");

        for (Student s : students) {

            displayArea.append(
                    "ID: " + s.id +
                    " | Name: " + s.name +
                    " | Age: " + s.age +
                    " | Courses: " + s.courses + "\n"
            );
        }
    }

    // ================= CLEAR =================
    void clearFields() {

        idField.setText("");
        nameField.setText("");
        ageField.setText("");
        courseField.setText("");
        searchField.setText("");
    }

    // ================= MAIN =================
    public static void main(String[] args) {

        new StudentManagementSystemAWT();
    }
}