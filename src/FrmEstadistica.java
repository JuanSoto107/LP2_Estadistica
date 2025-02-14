import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class FrmEstadistica extends JFrame {

    private JTextField txtDato;
    private JList lstMuestra;
    private JComboBox cmbEstadistica;
    private JTextField txtEstadistica;

    public FrmEstadistica() {
        setSize(400, 300);
        setTitle("Calculos estadísticos");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblDato = new JLabel("Dato");
        lblDato.setBounds(10, 10, 100, 25);
        getContentPane().add(lblDato);

        txtDato = new JTextField();
        txtDato.setBounds(80, 10, 100, 25);
        getContentPane().add(txtDato);

        JLabel lblMuestra = new JLabel("Muestra");
        lblMuestra.setBounds(210, 10, 100, 25);
        getContentPane().add(lblMuestra);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(80, 40, 100, 25);
        getContentPane().add(btnAgregar);

        JButton btnQuitar = new JButton("Quitar");
        btnQuitar.setBounds(80, 70, 100, 25);
        getContentPane().add(btnQuitar);

        lstMuestra = new JList();
        JScrollPane spMuestra = new JScrollPane(lstMuestra);
        spMuestra.setBounds(210, 40, 100, 150);
        getContentPane().add(spMuestra);

        JButton btnEstadistica = new JButton("Estadística");
        btnEstadistica.setBounds(10, 200, 100, 25);
        getContentPane().add(btnEstadistica);

        cmbEstadistica = new JComboBox();
        cmbEstadistica.setBounds(110, 200, 100, 25);
        String[] opciones = new String[] { "Sumatoria", "Promedio", "Desviación", "Máximo", "Mínimo", "Moda" };
        DefaultComboBoxModel mdlOpciones = new DefaultComboBoxModel(opciones);
        cmbEstadistica.setModel(mdlOpciones);
        getContentPane().add(cmbEstadistica);

        txtEstadistica = new JTextField();
        txtEstadistica.setBounds(210, 200, 100, 25);
        getContentPane().add(txtEstadistica);

        btnAgregar.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                agregarDato();
            }

        });

        btnQuitar.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                quitarDato();
            }

        });

        btnEstadistica .addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                calcularEstadistica();
                
            }

        });
    }

    private double[] muestra = new double[1000];//Declarar arreglo de 1000 posiciones
    private int totalDatos=-1;


    private void agregarDato() {
        double dato = Double.parseDouble(txtDato.getText()); //Convertir de texto a real
        totalDatos++;
        muestra[totalDatos] = dato;
        mostrarMuestra();
        txtDato.setText("");
    }

    private void mostrarMuestra() {
        String[] strMuestra=new String[totalDatos + 1];
        for(int i = 0; i <= totalDatos; i++){
            strMuestra[i] =String.valueOf(muestra[i]);
        }
        lstMuestra.setListData(strMuestra);
    }

    private void quitarDato() {
        if (lstMuestra.getSelectedIndex() >= 0) {
            for(int i=lstMuestra.getSelectedIndex();i<totalDatos;i++) { 
                muestra[i]= muestra[i + 1];

            }
            totalDatos--;
            mostrarMuestra();
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un dato");
        }

    }

    public double sumatoria() {
        double suma = 0;
        for(int i = 0;i <= totalDatos; i++) {
            suma += muestra[i];
            

        }

        return suma;
    }

    private double promedio() {
        double promedio = 0;
        if (totalDatos >= 0) {
            promedio = sumatoria() / (totalDatos + 1);
        }

        return promedio;

    }

    private double desviacionEstandar() {
        double suma = 0;
        double promediocalculado = promedio();
        for(int i = 0;i <= totalDatos; i++) {
            suma += Math.abs(muestra[i] - promediocalculado); 
            

        }
        if (totalDatos>=1) {
            return suma / totalDatos;

        } else {
            return 0;
        }

    }
 
    private double Maximo() {
        double mayor = muestra[0]; //asumir que el primer dato del vector muestra es el mayor de todos
        for(int i = 1; i <= totalDatos; i++) {
            if(mayor < muestra[i]) {   //asume la variable mayor como la posicion 0 del vector muestra y la compara con la posicion i del mismo vector i es la posicion 1
                mayor = muestra[i];     //Entonces despues de ubicar i=1 en el vector si la posicion 0 es menor que la posicion 1 el mayor pasa a ser la posicion 1, iterando asi sobre todos hasta hallar el mayor
            }

        }

        return mayor;

    }

    private void calcularEstadistica() {
        switch(cmbEstadistica.getSelectedIndex()) {
            case 0:
            txtEstadistica.setText(String.valueOf(sumatoria())); 
            break;
            case 1:
            txtEstadistica.setText(String.valueOf(promedio()));
            break;
            case 2:
            txtEstadistica.setText(String.valueOf(desviacionEstandar()));
            break;
            case 3:
            txtEstadistica.setText(String.valueOf(Maximo()));
            break;

        }
    }

}