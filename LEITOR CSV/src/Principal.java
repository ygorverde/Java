import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class Principal extends JFrame {

	private JPanel contentPane;
	private JTable tableDadosPacientes;
	private JScrollPane scrollPaneDados;
	private JComboBox comboBoxOpcaoOrdenamento;
	private JLabel lblNewLabelTempNanosegundo;



	// definindo o diretório onde a interface irá abrir como ponto principal
	String diretorioInicial = System.getProperty("user.home") + "\\Desktop";
	
	private int colunaParaordenacaoSelecionada;
	private int  i;
	//DefaultTableModel model = (DefaultTableModel) tableDadosPacientes.getModel();
	Paciente[] pa =new Paciente[1000];

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Principal() {

		setTitle("Relação de Pacientes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 975, 630);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnExcluirPaciente = new JButton("Excluir Paciente");
		JTextField textField = new JTextField();
		textField.setBounds(490, 535, 30, 23);
		contentPane.add(textField);
		btnExcluirPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnExcluirPaciente) {
					String ids = textField.getText();
					int id = Integer.parseInt(ids);
					excluirPaciente(pa, id);
				}

			}
		});
		btnExcluirPaciente.setBounds(329, 535, 140, 23);
		contentPane.add(btnExcluirPaciente);



		JButton btnInserirPaciente = new JButton("Inserir Paciente");
		contentPane.add(textField);
		btnExcluirPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnInserirPaciente.setBounds(548, 535, 140, 23);
		contentPane.add(btnInserirPaciente);




		JButton btnLocalizarArquivo = new JButton("Carregar Arquivo");
		btnLocalizarArquivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnLocalizarArquivo) {
					localizarArquivo();

				}
			}
		});
		btnLocalizarArquivo.setBounds(187, 535, 140, 23);
		contentPane.add(btnLocalizarArquivo);

		scrollPaneDados = new JScrollPane();
		scrollPaneDados.setBounds(10, 11, 939, 494);
		contentPane.add(scrollPaneDados);

		tableDadosPacientes = new JTable();
		scrollPaneDados.setViewportView(tableDadosPacientes);
		tableDadosPacientes.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Identificador", "CPF",
				"Nome", "Sobrenome", "Sexo", "Data de admissao", "Pais", "Diagnostico" }) {
			// procedimento que impedi a ediçaõ das celulas do jtable e naõ impede de
			// selecionar a linha que deseja
			boolean[] canEdit = new boolean[] { false, false, false, false, false, false, false, false };

			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});

		tableDadosPacientes.getColumnModel().getColumn(0).setPreferredWidth(99);
		tableDadosPacientes.getColumnModel().getColumn(1).setPreferredWidth(99);
		tableDadosPacientes.getColumnModel().getColumn(2).setPreferredWidth(99);
		tableDadosPacientes.getColumnModel().getColumn(3).setPreferredWidth(99);
		tableDadosPacientes.getColumnModel().getColumn(4).setPreferredWidth(99);
		tableDadosPacientes.getColumnModel().getColumn(5).setPreferredWidth(99);
		tableDadosPacientes.getColumnModel().getColumn(6).setPreferredWidth(99);
		tableDadosPacientes.getColumnModel().getColumn(7).setPreferredWidth(99);


		lblNewLabelTempNanosegundo = new JLabel("Execução em ns:");
		//lblNewLabelTempNanosegundo.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabelTempNanosegundo.setBounds(10, 510, 247, 23);
		contentPane.add(lblNewLabelTempNanosegundo);

		JComboBox comboBoxOpcaoOrdenamento = new JComboBox();
		comboBoxOpcaoOrdenamento.setModel(new DefaultComboBoxModel(new String[] {"Ordenação por:", "Identificador", "CPF", "Nome",
				"Sobrenome", "Sexo", "Data de admissao", "Pais", "Diagnostico", "MergeSort - Identificador", "MergeSort - Cpf",
                "MergeSort - Nome","MergeSort - Sobrenome","MergeSort - Sexo", "MergeSort - Data","MergeSort - País", "MergeSort - Diagnóstico",
                "Heapify - Identificador","Heapify - Cpf","Heapify - Nome","Heapify - sobrenome","Heapify - Sexo","Heapify - Data",
				"Heapify - País","Heapify - Diagnóstico"}));
		comboBoxOpcaoOrdenamento.setBounds(10, 535, 175, 23);
		contentPane.add(comboBoxOpcaoOrdenamento);
		
		comboBoxOpcaoOrdenamento.addActionListener(
			    new ActionListener(){
			       public void actionPerformed(ActionEvent evento){
			           colunaParaordenacaoSelecionada = comboBoxOpcaoOrdenamento.getSelectedIndex();
			           DefaultTableModel model = (DefaultTableModel) tableDadosPacientes.getModel();
			   		// verificando se a quantidade de linhas é maior que 0 no jtable, se for o model
			   		// recebe 0
			   		if (model.getRowCount() > 0) {
			   			atualizarOrdenacaoDaTabela(pa, i, colunaParaordenacaoSelecionada);
			   		}
			          
			       }
			    }
			    );
		


		JLabel labelID = new JLabel("ID:");
		labelID.setBounds(472,535,30,23);
		contentPane.add(labelID);


		JButton btnGerarArquivo = new JButton("Gerar arquivo ");
		btnGerarArquivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnGerarArquivo) {
					try {
					
					 	gerarArquivoCSV();
					} catch (IOException io) {
						// TODO Auto-generated catch block
						io.printStackTrace();
					}
				}

			}
		});
		btnGerarArquivo.setBounds(820, 535, 129, 23);
		contentPane.add(btnGerarArquivo);
		DefaultTableModel model = (DefaultTableModel) tableDadosPacientes.getModel();


	}


	// método para localizar e selecionar arquivo csv
	public void localizarArquivo() {

		// filtrando apenas arquivos com extensão .csv
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("Arquivos Executáveis (.csv)", "csv");

		// definindo o diretório onde a interface irá abrir como ponto principal
		// String diretorioInicial = System.getProperty("user.home") + "/Desktop";
		File diretorio = new File(diretorioInicial);

		// JFileChooser é uma interface gráfica de uma caixa de seleção de arquivos do
		// próprio java.
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(diretorio);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		// limitando apenas um arquivo selecionado, ao invés de vários, se colocar true
		// permite vários
		chooser.setAcceptAllFileFilterUsed(false);

		// logo devemos passar um filtro para dizer qual o tipo do arquivo
		chooser.addChoosableFileFilter(filtro);
		// declarando variável e atribuindo nada
		String caminhoArquivo = "";

		int retorno = chooser.showOpenDialog(null);
		// verificacao se o campo foi preenchido
		if (retorno == JFileChooser.APPROVE_OPTION) {
			// passando arquivo selecionado
			caminhoArquivo = chooser.getSelectedFile().getAbsolutePath();
			// chamando método carregarArquivo
			carregarArquivo(caminhoArquivo);

		}
	}

	
	public void carregarArquivo(String caminhoArquivo) {
		try {
			
			BufferedReader csvConteudo = new BufferedReader(new FileReader(caminhoArquivo));// arquivo

			String linha = csvConteudo.readLine();
			String separadorCampo = ",";
			int cont = 0;
			while (linha != null) {
				cont++;
				if (cont - 1 != 0) {
					// passando para o objeto array
					String[] paciente = linha.split(separadorCampo);
		
					String id=paciente[0];
					String cpf=paciente[1];
					String nome=paciente[2];
					String sobrenome=paciente[3];
					String sexo=paciente[4];
					String dataadmissao=paciente[5];
					String pais=paciente[6];
					String diagnostico=paciente[7];
					Paciente p= new Paciente(id,cpf,nome,sobrenome, sexo,dataadmissao,pais,diagnostico);
					pa[i]=p;
					i++;	
				}
				linha = csvConteudo.readLine();
			} // fim while

			atualizarOrdenacaoDaTabela(pa, i,colunaParaordenacaoSelecionada);	

		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(null, "Arquivo não encontrado");
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "IO erro");
		}
	}
	



	public void atualizarOrdenacaoDaTabela(Paciente[] pa, int quant, int colunaParaordenacaoSelecionada) {
		OrdenacaoPorColuna opc = new OrdenacaoPorColuna();


		// DefaultTableModel serve para colocar os dados (colunas e linhas) de uma
		// tabela, separado do objeto tabela
		DefaultTableModel model = (DefaultTableModel) tableDadosPacientes.getModel();
		// verificando se a quantidade de linhas é maior que 0 no jtable, se for o model
		// recebe 0
		if (model.getRowCount() > 0) {
			model.setRowCount(0);
		}

		if(colunaParaordenacaoSelecionada != 0){
			long tempoInicial = System.nanoTime();
			opc.verificarColunaOrdenacao(pa,quant,colunaParaordenacaoSelecionada);
			long tempoFinal = System.nanoTime();
			lblNewLabelTempNanosegundo.setText( "Executado em: "+ (tempoFinal - tempoInicial) + " ns");
		}

		//Vector<Object> dadoadicional = new Vector();

		//String[] novoarray = new String[quant + 1];
		for(Paciente p:pa) {
			Vector<Object> dado = new Vector();

			dado.add(p.getId());
			dado.add(p.getCPF());
			dado.add(p.getNome());
			dado.add(p.getSobrenome());
			dado.add(p.getSexo());
			dado.add(p.getDataadmissao());
			dado.add(p.getPais());
			dado.add(p.getDiagnostico());
			model.addRow(dado);
		}
		//model.addRow(novoarray);

	}

	public void excluirPaciente(Paciente[] pa, int id){
		Long[] identificador = new Long[pa.length];

		for(int x = 0; x < pa.length; x++){
			identificador[x] = Long.parseLong(pa[x].getId());
		}
		Paciente[] novoarray =new Paciente[pa.length - 1];

		for(int x = 0; x < pa.length - 1; x++){
			if(identificador[x] < id){
				novoarray[x] = pa[x];
			}else{
				novoarray[x] = pa[x + 1];
			}
		}
		for(int x = 0; x < pa.length - 1; x++){
			atualizarOrdenacaoDaTabela(novoarray, novoarray.length, 1);
				pa[x] = novoarray[x];
		}

	}

	public void gerarArquivoCSV() throws IOException {
		// atribuindo o diretorio onde deve abrir a interface
		JFileChooser diretorioArqSalv = new JFileChooser(diretorioInicial);
		// titulo da caixa de dialogo
		diretorioArqSalv.setDialogTitle("Save As");
		// determinando extensão do arquivo para exibir na interface
		FileNameExtensionFilter descrArq = new FileNameExtensionFilter("EXCEL FILES", "csv");
		diretorioArqSalv.setFileFilter(descrArq);
		int btsalv = diretorioArqSalv.showSaveDialog(null);

		// verificando se o botao de salvar foi clicado
		if (btsalv == JFileChooser.APPROVE_OPTION) {

			// local, nome do arquivo concatenando com a extensão
			File arqCSV = new File(diretorioArqSalv.getSelectedFile() + ".csv");

			// Caso o arquivo não exista então cria-se um novo arquivo
			if (!arqCSV.exists()) {
				arqCSV.createNewFile();
			}

			// FileWriter e BufferedWriter servem para escrever arquivos texto
			// pegando o caminho do arquivo absoluto, e para escrever o arquivo pelo
			// BufferedWrite
			try (FileWriter fw = new FileWriter(arqCSV.getAbsoluteFile()); BufferedWriter bw = new BufferedWriter(fw)) {
				// Laço que percorre as colunas da jTable recuperando o nome das mesmas
				for (int i = 0; i < tableDadosPacientes.getColumnCount(); i++) {
					bw.write(tableDadosPacientes.getModel().getColumnName(i) + ",");
				}

				// Quebra de linha no arquivo(lembrando que no windows: \r\n | Linux: \n)
				bw.write("\r\n");

				// Laço que percorre as linhas da jTable
				for (int i = 0; i < tableDadosPacientes.getRowCount(); i++) {

					// Laço que percorre as colunas da jTable recuperando os valores
					for (int j = 0; j < tableDadosPacientes.getColumnCount(); j++) {
						bw.write(tableDadosPacientes.getModel().getValueAt(i, j) + ",");
					}

					bw.write("\r\n");
				}

				JOptionPane.showMessageDialog(null, "Dados exportados com sucesso!");
			} catch (HeadlessException | IOException ex) {
				JOptionPane.showMessageDialog(null, "Erro ao exportar dados da jTable!");
			}

		}
	}
}
