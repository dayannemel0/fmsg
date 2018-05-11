package lato.nt1.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import lato.nt1.bd.PetDAO;
import lato.nt1.model.Pet;

public class TelaPrincipal extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	JLabel labelNome;
	JLabel labelTipo;
	JLabel labelIdade;
	JLabel labelPeso;
	JLabel labelTamanho;
	
	JTextField textNome;
	JTextField textTipo;
	JTextField textIdade;
	JTextField textPeso;
	JComboBox<String> comboTamanho;
	
	JButton buttonSalvar;
	
	JTable tablePets;
	DefaultTableModel modelTablePets;
	JScrollPane scrollTablePets;
	
	JButton buttonDeletar;

	public static void main(String[] args) {
		TelaPrincipal telaPrincipal = new TelaPrincipal();
		telaPrincipal.executar();
	}
	
	private void executar() {
		setTitle("5LATO PET");
		setSize(800, 650);
		setLayout(null);
		
		labelNome = new JLabel("Nome: ");
		labelNome.setBounds(50,40,100,20);
		getContentPane().add(labelNome);
		
		labelTipo = new JLabel("Tipo: ");
		labelTipo.setBounds(50,80,100,20);
		getContentPane().add(labelTipo);
		
		labelIdade = new JLabel("Idade: ");
		labelIdade.setBounds(50,120,100,20);
		getContentPane().add(labelIdade);
		
		labelPeso = new JLabel("Peso: ");
		labelPeso.setBounds(50,160,100,20);
		getContentPane().add(labelPeso);
		
		labelTamanho = new JLabel("Tamanho: ");
		labelTamanho.setBounds(50,200,100,20);
		getContentPane().add(labelTamanho);
		
		
		textNome = new JTextField();
		textNome.setBounds(150,40,200,20);
		getContentPane().add(textNome);
		
		textTipo = new JTextField();
		textTipo.setBounds(150,80,200,20);
		getContentPane().add(textTipo);
		
		textIdade = new JTextField();
		textIdade.setBounds(150,120,100,20);
		getContentPane().add(textIdade);

		textPeso = new JTextField();
		textPeso.setBounds(150,160,100,20);
		getContentPane().add(textPeso);
		
		comboTamanho = new JComboBox<>();
		comboTamanho.addItem("Pequeno");
		comboTamanho.addItem("Médio");
		comboTamanho.addItem("Grande");
		comboTamanho.setBounds(150,200,200,20);
		getContentPane().add(comboTamanho);
		
		buttonSalvar = new JButton("Salvar");
		buttonSalvar.setBounds(50,240,120,20);
		buttonSalvar.addActionListener(this);
		getContentPane().add(buttonSalvar);
				
		modelTablePets = new DefaultTableModel();
		tablePets = new JTable(modelTablePets);
		modelTablePets.addColumn("Cod.");
		modelTablePets.addColumn("Nome");
		modelTablePets.addColumn("Tipo");
		modelTablePets.addColumn("Idade");
		modelTablePets.addColumn("Peso");
		modelTablePets.addColumn("Tamanho");
		tablePets.getColumnModel().getColumn(0).setPreferredWidth(45);
		tablePets.getColumnModel().getColumn(1).setPreferredWidth(275);
		tablePets.getColumnModel().getColumn(2).setPreferredWidth(200);
		tablePets.getColumnModel().getColumn(3).setPreferredWidth(50);
		tablePets.getColumnModel().getColumn(4).setPreferredWidth(50);
		tablePets.getColumnModel().getColumn(5).setPreferredWidth(80);
		scrollTablePets = new JScrollPane(tablePets);
		scrollTablePets.setBounds(50,280,700,280);
		getContentPane().add(scrollTablePets);
		
		buttonDeletar = new JButton("Deletar");
		buttonDeletar.setBounds(50,580,120,20);
		buttonDeletar.addActionListener(this);
		getContentPane().add(buttonDeletar);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		ListarPetBD listarPetBD = new ListarPetBD();
		listarPetBD.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(buttonSalvar)) {
			
			
			if(textNome.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Informe o nome do pet.", "Campo Obrigatório", JOptionPane.INFORMATION_MESSAGE);
				textNome.requestFocus();
			}else if(textTipo.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Informe o tipo do pet.", "Campo Obrigatório", JOptionPane.INFORMATION_MESSAGE);
				textTipo.requestFocus();
			}else if(textIdade.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Informe a idade do pet.", "Campo Obrigatório", JOptionPane.INFORMATION_MESSAGE);
				textIdade.requestFocus();
			}else if(textPeso.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Informe o peso do pet.", "Campo Obrigatório", JOptionPane.INFORMATION_MESSAGE);
				textPeso.requestFocus();
			}else {
				Pet pet = new Pet();
				pet.setNome(textNome.getText());
				pet.setTipo(textTipo.getText());
				try {
					pet.setIdade(Integer.parseInt(textIdade.getText()));
				}catch(Exception ex) {
					pet.setIdade(0);
				}
				try {
					pet.setPeso(Double.parseDouble(textPeso.getText()));
				}catch(Exception ex) {
					pet.setPeso(0);
				}
				pet.setTamanho((String) comboTamanho.getSelectedItem());
				
				SalvarPetBD salvarPetBD = new SalvarPetBD(pet);
				salvarPetBD.start();
			}
			
		}else if(e.getSource().equals(buttonDeletar)) {
			if(tablePets.getSelectedRowCount() > 0) {
				int codigo = (int) tablePets.getValueAt(tablePets.getSelectedRow(), 0);
				if(JOptionPane.showConfirmDialog(this, "Confirma que deseja deletar o Pet selecionado?") == 0) {
					DeletarPetBD deletarPetBD = new DeletarPetBD(codigo);
					deletarPetBD.start();
				}
			}else {
				JOptionPane.showMessageDialog(this, "Selecione um pet para deletar.", "Pet não Selecionado", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
	}
	
	public void limparCampos() {
		textNome.setText("");
		textTipo.setText("");
		textIdade.setText("");
		textPeso.setText("");
		comboTamanho.setSelectedIndex(0);
	}
	
	private class SalvarPetBD extends Thread {
		
		private Pet pet;
		
		public SalvarPetBD(Pet pet) {
			this.pet = pet;
		}
		
		@Override
		public void run() {
			PetDAO petDAO = new PetDAO();
			try {
				buttonSalvar.setEnabled(false);
				buttonSalvar.setText("Salvando...");
				
				petDAO.openConnection();
				petDAO.salvar(pet);
				
				ListarPetBD listarPetBD = new ListarPetBD();
				listarPetBD.start();
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(TelaPrincipal.this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			}finally {
				buttonSalvar.setText("Salvar");
				buttonSalvar.setEnabled(true);
				petDAO.closeConnection();
			}
		}
		
	}
	
	private class DeletarPetBD extends Thread {
		
		private int codigo;
		
		public DeletarPetBD(int codigo) {
			this.codigo = codigo;
		}
		
		@Override
		public void run() {
			PetDAO petDAO = new PetDAO();
			try {		
				buttonDeletar.setEnabled(false);
				buttonDeletar.setText("Deletando...");
				
				petDAO.openConnection();
				petDAO.deletar(codigo);
				
				ListarPetBD listarPetBD = new ListarPetBD();
				listarPetBD.start();
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(TelaPrincipal.this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			}finally {
				buttonDeletar.setText("Deletar");
				buttonDeletar.setEnabled(true);
				petDAO.closeConnection();
			}
		}
		
	}
	
	private class ListarPetBD extends Thread {
						
		@Override
		public void run() {
			PetDAO petDAO = new PetDAO();
			try {				
				petDAO.openConnection();
				List<Pet> pets = petDAO.retornarTodosRegistros();
				modelTablePets.setNumRows(0);
				for(Pet pet : pets) {
					modelTablePets.addRow(new Object[]{pet.getCodigo(), pet.getNome(), pet.getTipo(), pet.getIdade(), pet.getPeso(), pet.getTamanho()});
				}
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(TelaPrincipal.this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			}finally {
				petDAO.closeConnection();
			}
		}
		
	}

}
