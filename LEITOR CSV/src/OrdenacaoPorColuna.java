import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Array;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class OrdenacaoPorColuna {

	public void verificarColunaOrdenacao(Paciente[] pacientes, int qtd, int colunaParaordenacaoSelecionada){

		Long[] identificador = new Long[pacientes.length];
		Long[] cpf = new Long[pacientes.length];
		String[] nome = new String[pacientes.length];
		String[] sobrenome= new String[pacientes.length];
		String[] sexo = new String[pacientes.length];
		String[] dataadmissao = new String[pacientes.length];
		String[] pais = new String[pacientes.length];
		String[] diagnostico = new String[pacientes.length];
		for(int x = 0; x<qtd; x++){
			identificador[x] = Long.parseLong(pacientes[x].getId());
			cpf[x] = Long.parseLong(pacientes[x].getCPF());
			nome[x] = pacientes[x].getNome();
			sobrenome[x] = pacientes[x].getSobrenome();
			sexo[x] = pacientes[x].getSexo();
			dataadmissao[x] = pacientes[x].getDataadmissao();
			pais[x] = pacientes[x].getPais();
			diagnostico[x] = pacientes[x].getDiagnostico();
		}

		String[] data = new String[pacientes.length];
		String[] dia = new String[pacientes.length];
		String[] mes = new String[pacientes.length];
		String[] ano= new String[pacientes.length];

		for(int x = 0; x < qtd; x++){
			String[] vetorseparado = dataadmissao[x].split("/");
			dia[x] = vetorseparado[0];
			mes[x] = vetorseparado[1];
			ano[x] = vetorseparado[2];
			data[x] = ano[x] + mes[x] + dia[x];
			//System.out.println(data[x]);
		}

		switch (colunaParaordenacaoSelecionada) {

			case 0:
				break;

			case 1:
				ordenarLong(identificador, pacientes);
				break;

			case 2:
				ordenarLong(cpf, pacientes);
				break;

			case 3:
				ordenarString(nome, pacientes);
				break;

			case 4:
				ordenarString(sobrenome, pacientes);
				break;

			case 5:
				ordenarString(sexo, pacientes);
				break;

			case 6:
				ordenarString(data, pacientes);
				break;

			case 7:
				ordenarString(pais, pacientes);
				break;

			case 8:
				ordenarString(diagnostico, pacientes);
				break;

			case 9:
				mergeSort(identificador, 0, identificador.length-1, pacientes);
				break;

			case 10:
				mergeSort(cpf, 0, identificador.length-1, pacientes);
				break;

			case 11:
				mergeSortString(nome, 0, nome.length-1, pacientes);
				break;

			case 12:
				mergeSortString(sobrenome, 0, nome.length-1, pacientes);
				break;

			case 13:
				mergeSortString(sexo, 0, nome.length-1, pacientes);
				break;

			case 14:
				mergeSortString(data, 0, nome.length-1, pacientes);
				break;

			case 15:
				mergeSortString(pais, 0, nome.length-1, pacientes);
				break;

			case 16:
				mergeSortString(diagnostico, 0, nome.length-1, pacientes);
				break;
			/*Heapify*/
			case 17:
				sort(identificador, pacientes);
				System.out.println(pacientes.length);
				break;

			case 18:
				sort(cpf, pacientes);
				break;

			case 19:
				sortString(nome, pacientes);
				break;

			case 20:
				sortString(sobrenome, pacientes);
				break;

			case 21:
				sortString(sexo, pacientes);
				break;

			case 22:
				sortString(data, pacientes);
				break;

			case 23:
				sortString(pais, pacientes);
				break;

			case 24:
				sortString(diagnostico, pacientes);
				break;

			default:
			System.out.println("O valor informado inválido!");


		}

	}

	void ordenarLong(Long[]campo, Paciente[]pacientes){
		int qtd = campo.length;
		for (int j = 1; j < qtd; j++) {
			long temp;
			Paciente p;
			int i;
			temp = campo[j];
			p = pacientes[j];
			for (i = j - 1; (i >= 0) && (campo[i] > temp); i--) {
				campo[i + 1] = campo[i];
				pacientes[i + 1] = pacientes[i];
			}
			campo[i + 1] = temp;
			pacientes[i + 1] = p;
		}
	}

	void ordenarString(String[]campo, Paciente[]pacientes){
		int qtd = campo.length;
		for (int j = 1; j < qtd; j++) {
			Paciente p;
			String x;
			int i;
			p = pacientes[j];
			x = campo[j];
			i = j - 1;
			while (i >= 0) {
				if (x.compareTo(campo[i]) > 0) {
					break;
				}
				pacientes[i +1] = pacientes[i];
				campo[i + 1] = campo[i];
				i--;
			}
			pacientes[i+1] = p;
			campo[i + 1] = x;
		}
	}

	void mergeSort(Long[]identificador, int l, int r, Paciente[] pacientes){
	if(l < r){
		int m = ( l + r) /2;
		mergeSort(identificador, l, m, pacientes);
		mergeSort(identificador, m+1, r, pacientes);
		intercalar(identificador, l, m, r, pacientes);
	}

}

	void mergeSortString(String[]campo, int l, int r, Paciente[] pacientes){
		if(l < r){
			int m = ( l + r) /2;
			mergeSortString(campo, l, m, pacientes);
			mergeSortString(campo, m+1, r, pacientes);
			intercalarString(campo, l, m, r, pacientes);
		}
	}

	void intercalarString (String[]campo, int l, int m, int r, Paciente[] pacientes){
		int n1 = m - l + 1;
		int n2 = r - m;

		String L[] = new String[n1];
		String R[] = new String[n2];

		Paciente[] pL =new Paciente[n1];
		Paciente[] pR =new Paciente[n2];

		for (int i=0; i<n1; i++){
			L[i] = campo [l+i];
			pL[i] = pacientes [l+i];

		}
		for (int j=0; j<n2; j++){
			R[j] = campo [m + 1 + j];
			pR[j] = pacientes [m + 1 + j];
		}

		int i = 0, j = 0;

		int k = l;
		while( i < n1 && j < n2){
			if(L[i].compareTo(R[j]) <= 0){
				campo[k] = L[i];
				pacientes[k] = pL[i];
				i++;
			}
			else{
				campo[k] = R[j];
				pacientes[k] = pR[j];
				j++;
			}
			k++;
		}

		while (i < n1){
			campo[k] = L[i];
			pacientes[k] = pL[i];
			i++;
			k++;
		}

		while(j < n2){
			campo[k] = R[j];
			pacientes[k] = pR[j];
			j++;
			k++;
		}


	}

	void intercalar (Long[] identificador, int l, int m, int r, Paciente[] pacientes){
		int n1 = m - l + 1;
		int n2 = r - m;

		Long L[] = new Long[n1];
		Long R[] = new Long[n2];

		Paciente[] pL =new Paciente[n1];
		Paciente[] pR =new Paciente[n2];

		for (int i=0; i<n1; i++){
			L[i] = identificador [l+i];
			pL[i] = pacientes [l+i];

		}
		for (int j=0; j<n2; j++){
			R[j] = identificador [m + 1 + j];
			pR[j] = pacientes [m + 1 + j];
		}

		int i = 0, j = 0;

		int k = l;
		while( i < n1 && j < n2){
			if(L[i] <= R[j]){
				identificador[k] = L[i];
				pacientes[k] = pL[i];
				i++;
			}else{
				identificador[k] = R[j];
				pacientes[k] = pR[j];
				j++;
			}
			k++;
		}

		while (i < n1){
			identificador[k] = L[i];
			pacientes[k] = pL[i];
			i++;
			k++;
		}

		while(j < n2){
			identificador[k] = R[j];
			pacientes[k] = pR[j];
			j++;
			k++;
		}

	}

	public void sort(Long[] arr, Paciente[] p)
	{
		int n = arr.length;

		//Montar heap (reorganizar matriz)
		for (int i = n / 2 - 1; i >= 0; i--)
			heapify(arr, n, i, p);

		// Um ​​por um extrai um elemento do heap
		for (int i=n-1; i>0; i--)
		{
			// Move current root to end
			Long temp = arr[0];
			Paciente pac = p[0];
			arr[0] = arr[i];
			p[0] = p[i];
			arr[i] = temp;
			p[i] = pac;

		// chama max heapify na pilha reduzida
			heapify(arr, i, 0, p);
		}
	}

	void heapify(Long arr[], int n, int i, Paciente[] p)
	{
		int largest = i; // Inicializa o maior como raiz
		int l = 2*i + 1; // left = 2*i + 1
		int r = 2*i + 2; // right = 2*i + 2

		// Se o filho esquerdo for maior que a raiz
		if (l < n && arr[l] > arr[largest])
			largest = l;

		// Se o filho da direita for maior que o maior até agora
		if (r < n && arr[r] > arr[largest])
			largest = r;

		// Se o maior não for raiz
		if (largest != i)
		{
			Long swap = arr[i];
			Paciente pac = p[i];
			arr[i] = arr[largest];
			p[i] = p[largest];
			arr[largest] = swap;
			p[largest] = pac;

		// Amontoa recursivamente a subárvore afetada
			heapify(arr, n, largest, p);
		}
	}

	void heapifyString(String arr[], int n, int i, Paciente[] p)
	{
		int largest = i; // Inicializa o maior como raiz
		int l = 2*i + 1; // left = 2*i + 1
		int r = 2*i + 2; // right = 2*i + 2

		// Se o filho esquerdo for maior que a raiz
		if (l < n && (arr[l].compareTo(arr[largest]) > 0))
			largest = l;

		// Se o filho da direita for maior que o maior até agora
		if (r < n && (arr[r].compareTo(arr[largest]) > 0))
			largest = r;

		// Se o maior não for raiz
		if (largest != i)
		{
			String swap = arr[i];
			Paciente pac = p[i];
			arr[i] = arr[largest];
			p[i] = p[largest];
			arr[largest] = swap;
			p[largest] = pac;

			// Amontoa recursivamente a subárvore afetada
			heapifyString(arr, n, largest, p);
		}
	}

	public void sortString(String[] arr, Paciente[] p)
	{
		int n = arr.length;

		// Montar amontoamento (reorganizar array)
		for (int i = n / 2 - 1; i >= 0; i--)
			heapifyString(arr, n, i, p);

		// Um ​​por um extrai um elemento do amontoamento
		for (int i=n-1; i>0; i--)
		{
			// Move current root to end
			String temp = arr[0];
			Paciente pac = p[0];
			arr[0] = arr[i];
			p[0] = p[i];
			arr[i] = temp;
			p[i] = pac;

			// chama max heapify na pilha reduzida
			heapifyString(arr, i, 0, p);
		}
	}


}





