package thread;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TelaTimeThread extends JDialog{

	// Componentes
	
	private JPanel jPanel = new JPanel(new GridBagLayout()); // Painel de componentes
	
	private JLabel descricaoHora = new JLabel("Time Thread 1");
	private JTextField mostraTempo = new JTextField();
	
	private JLabel descricaoHora2 = new JLabel("Time Thread 2");
	private JTextField mostraTempo2 = new JTextField();
	
	private JButton jButton = new JButton("Start");
	private JButton jButton2 = new JButton("Stop");
	
	private Runnable thread1 = new Runnable() {
		
		@Override
		public void run() {
			while(true) { // Fica sempre rodando
				mostraTempo.setText(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").
						format(Calendar.getInstance().getTime()));
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
			
		}
	};
	
  private Runnable thread2 = new Runnable() {
		
		@Override
		public void run() {
			while(true) { // Fica sempre rodando
				mostraTempo2.setText(new SimpleDateFormat("dd/MM/yyyy hh:mm.ss").
						format(Calendar.getInstance().getTime()));
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
			
		}
	};
	
	private Thread thread1Time;
	private Thread thread2Time;
	
	public TelaTimeThread() { // Executa oque tiver dentro no momento da abertura ou execução
		
		// Primeira parte concluida!
		setTitle("Minha tela de time com Thread"); // Titulo
		setSize(new Dimension(240, 240)); // Dimension: Largura e Altura
		setLocationRelativeTo(null); // Centralizar no meio da tela
		setResizable(false); // Proibe do usuario aumentar ou diminuir a tela


		// Segunda parte   //Gerenciador de layout
		GridBagConstraints gridBagConstraints = new GridBagConstraints();// Controlador de posionamento de botões na tela
		gridBagConstraints.gridx = 0; // X = Linhas (na posição 0. Primeira posição).
		gridBagConstraints.gridy = 0; // Y = Colunas (na posição 0. Primeira posição).
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.insets = new Insets(5, 10, 5, 5);
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		
		// ----------------------------------------------------
		descricaoHora.setPreferredSize(new Dimension(200, 25));
		jPanel.add(descricaoHora, gridBagConstraints);
		
		mostraTempo.setPreferredSize(new Dimension(200, 25));
		gridBagConstraints.gridy ++;
		mostraTempo.setEditable(false); // Não pode Editar
		jPanel.add(mostraTempo, gridBagConstraints);
		
		descricaoHora2.setPreferredSize(new Dimension(200, 25));
		gridBagConstraints.gridy ++;
		mostraTempo2.setEditable(false); // Não pode Editar
		jPanel.add(descricaoHora2, gridBagConstraints);
		
		mostraTempo2.setPreferredSize(new Dimension(200, 25));
		gridBagConstraints.gridy ++;
		jPanel.add(mostraTempo2, gridBagConstraints);
		
		// -------------------------------------------------
		gridBagConstraints.gridwidth = 1;
		
		jButton.setPreferredSize(new Dimension(95, 25));
		gridBagConstraints.gridy ++;
		jPanel.add(jButton, gridBagConstraints);
		
		jButton2.setPreferredSize(new Dimension(92, 25));
		gridBagConstraints.gridx ++;
		jPanel.add(jButton2, gridBagConstraints);
		
		jButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) { // Execute o clique no botão
				thread1Time = new Thread(thread1);
				thread1Time.start();
				
				thread2Time = new Thread(thread2);
				thread2Time.start();
				
				jButton.setEnabled(false);
				jButton2.setEnabled(true);
			}
		});
		
		jButton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				thread1Time.stop();
				thread2Time.stop();
				
				jButton.setEnabled(true);
				jButton2.setEnabled(false);
			}
		});
		
		
		jButton2.setEnabled(false); // Desabilitar Stop
		
		add(jPanel, BorderLayout.WEST);
		// Sempre será o ultimo comando
		setVisible(true); // Torna a tela vívisvel para o usuário
		
	}
}
