import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JSeparator;
import javax.swing.JCheckBox;
import java.awt.GridLayout;
import javax.swing.JTextArea;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.TextField;
import java.util.ArrayList;
import java.util.Date;
import java.util.EventListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;

public class Main extends JFrame 
{

	private JPanel contentPane;
	private JFrame frame;

	// Start värden
	static String startWalue = "0";
	static String startAmount = "1";
	static String startMo = "10";
	static String startLo = "25";
	static String startAffo = "20";
	static String startVinst = "25";
	
	//Nya värden
	static String overAmount;
	static String overMo;
	static String overLo;
	static String overAffo;
	static String overVinst;
	JList listYourPrepareTime;
	JList listYourUnit;
	JList listYourLo;
	JList listYourAffo;
	JList listYourProfit;
	JList listYourPris;

	DefaultListModel<String> CollectedMaterialCost = new DefaultListModel<String>();

	
	static Object rowDataMachine[][] = {};
	static String columnNamesMachin[] = { "Nummer", "Maskin", "Pris pre timme", "Antal" ,"Stäl tid", "Oprations tid", "Pris" };
	static DefaultTableModel dtmMachine = new DefaultTableModel(rowDataMachine, columnNamesMachin);
	//Matrial Tabel
	static Object rowDataMatrial[][] = {};
	static String columnNamesMatrial[] = { "Matrial", "Pris/Enhet", "Mängd", "MO", "Affo", "Vinst", "Pris" };
	static DefaultTableModel dtmMarieial = new DefaultTableModel(rowDataMatrial, columnNamesMatrial);
	//Your Tabel
	static Object rowDataYour[][] = {};
	static String columnNamesYour[] = { "Era Kostnader", "Stälkostnad", "St Pris", "St","LO", "Affo", "Vinst", "Pris" };
	static DefaultTableModel dtmYour = new DefaultTableModel(rowDataYour, columnNamesYour);
	
	
	//Machin Tabel cost
	static Object rowDataMachineCost[][] = {};
	static String columnNamesMachineCost[] = { "Maskin Kostnader" };
	static DefaultTableModel dtmMachineCost = new DefaultTableModel(rowDataMachineCost, columnNamesMachineCost);
	//Matral Tabel cost
	static Object rowDataMatrialCost[][] = {};
	static String columnNamesMatrialCost[] = { "Matrial kostnader" };
	static DefaultTableModel dtmMarieialCost = new DefaultTableModel(rowDataMatrialCost, columnNamesMatrialCost);
	// Your Tabel cost
	static Object rowDataYourCost[][] = {};
	static String columnNamesYourCost[] = { "Era kostnader" };
	static DefaultTableModel dtmYourCost = new DefaultTableModel(rowDataYourCost, columnNamesYourCost);
	// Preptime Tabel 
	static Object rowDataPrepTime[][] = {};
	static String columnNamesPrepTime[] = { "Förberedelse tid" };
	static DefaultTableModel dtmPrepTime = new DefaultTableModel(rowDataPrepTime, columnNamesPrepTime);
	// OprerationTime Tabel 
	static Object rowDataOperationTime[][] = {};
	static String columnNamesOprerationTime[] = { "Operation tid" };
	static DefaultTableModel dtmOperationTime = new DefaultTableModel(rowDataOperationTime, columnNamesOprerationTime);
	
	
	int maskinprisenhet;


	private static JTextField textFieldDatum;
	private JTextField textProduce;
	private JTextField textAmount;
	private JTextField textVinst;
	private JTextField textAffo;
	private JTextField textLo;
	private JTextField textMo;
	private JTextField textImport;
	private static JTextField textDate;
	private JTextField textTotalAmount;
	private JTextField textCostemerName;
	private JTextField textTotalMaterialCost;
	private JTextField textTotalMachineCost;
	private JTextField textYourTotalCost;
	private JTextField textTotalPrepareTime;
	private JTextField textTotalMachineTime;
	private JTextField textShippingCost;
	private JTextField textTotalTime;
	private JTextField textFieldUnitAmaunt;

	List<Integer> NR = new ArrayList<Integer>();
	List<String> Maskin = new ArrayList<String>();
	private static JTable tableCollectedMateralCost;
	private JTable tableCollectedYourCost;
	private static JTable Yourtable;
	private static JTable Matrialtable;
	private static JTable Machinetable;
	private JTable tableCollectedMachineCost;
	private JTable tableCollectedPrepTime;
	private JTable tableCollectedOperationTime;


	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run() 
			{
				try 
				{
					Main frame = new Main();
					frame.setVisible(true);
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	public Main() 
	{
		initialize();

	}


	private void initialize() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1500, 900);// ändra när innan färdigg //TODO
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);

		maintables();
		panelMaterial();
		panelMachineLists();
		panelKostnader();
		otherTabels();
		buttons();
		textfelds();
		label();
		lisnerMachine();
		lisnerMarial();
		lisnerYour();
		setStarWalue();
		setStartProcentWalue();
		setDate();
		AddEx();

	}

	

	private void maintables() 
	{
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPaneMasikin = new JScrollPane();
		scrollPaneMasikin.setBounds(10, 127, 980, 65);
		contentPane.add(scrollPaneMasikin);
		
		Machinetable = new JTable();
		scrollPaneMasikin.setViewportView(Machinetable);

		JScrollPane scrollPaneMatrial = new JScrollPane();
		scrollPaneMatrial.setBounds(10, 248, 980, 65);
		contentPane.add(scrollPaneMatrial);
		
		Matrialtable = new JTable();
		scrollPaneMatrial.setViewportView(Matrialtable);
		scrollPaneMatrial.setVisible(true);

		JScrollPane scrollPaneKostnader = new JScrollPane();
		scrollPaneKostnader.setBounds(10, 374, 980, 65);
		contentPane.add(scrollPaneKostnader);
		
		Yourtable = new JTable();
		scrollPaneKostnader.setViewportView(Yourtable);
		scrollPaneKostnader.setVisible(true);
	}

	private void panelMachineLists() 
	{
		Machinetable.setModel(dtmMachine);
		dtmMachine.addTableModelListener(new TableModelListener()
		{
			public void tableChanged(TableModelEvent e) 
			{

				int row = e.getLastRow();
				int col = e.getColumn();

				if (col >= 0)
				{
					Object newData = Machinetable.getValueAt(row, col);
					NewTabelPoste(col, newData);
					SumCost(tableCollectedMachineCost,textTotalMachineCost);
					SumCost(tableCollectedPrepTime,textTotalPrepareTime);
					SumCost(tableCollectedOperationTime,textTotalMachineTime);
				}
			}
		});
	}
	

	private void panelMaterial()
	{
		Matrialtable.setModel(dtmMarieial);
		dtmMarieial.addTableModelListener(new TableModelListener()
		{
			public void tableChanged(TableModelEvent e) 
			{

				int row = e.getLastRow();
				int col = e.getColumn();

				if (col >= 0)
				{
					Object newData = Matrialtable.getValueAt(row, col);
					NewTabelPoste(col, newData);
					SumCost(tableCollectedMateralCost,textTotalMaterialCost);
				}
			}
		});
	}

	private void panelKostnader()
	{

		Yourtable.setModel(dtmYour);
		dtmYour.addTableModelListener(new TableModelListener() 
		{
			public void tableChanged(TableModelEvent e)
			{
				int row = e.getLastRow();
				int col = e.getColumn();

				if (col >= 0)
				{
					Object newData = Yourtable.getValueAt(row, col);
					NewTabelPoste(col, newData);
					SumCost(tableCollectedYourCost,textYourTotalCost);
				}
			}
		});
	}

	private void otherTabels() 
	{
		
		JScrollPane scrollPaneMachineCost = new JScrollPane();
		scrollPaneMachineCost.setBounds(55, 493, 100, 122);
		contentPane.add(scrollPaneMachineCost);
		
		tableCollectedMachineCost = new JTable();
		tableCollectedMachineCost.setDefaultEditor(Object.class, null);
		scrollPaneMachineCost.setViewportView(tableCollectedMachineCost);
		tableCollectedMachineCost.setModel(dtmMachineCost);
		
		JScrollPane scrollPaneMatreialCost = new JScrollPane();
		scrollPaneMatreialCost.setBounds(201, 493, 106, 122);
		contentPane.add(scrollPaneMatreialCost);
		
		tableCollectedMateralCost = new JTable();
		tableCollectedMateralCost.setDefaultEditor(Object.class, null);
		scrollPaneMatreialCost.setViewportView(tableCollectedMateralCost);
		tableCollectedMateralCost.setModel(dtmMarieialCost);
		
		JScrollPane scrollPaneYourCost = new JScrollPane();
		scrollPaneYourCost.setBounds(347, 493, 100, 122);
		contentPane.add(scrollPaneYourCost);
		
		tableCollectedYourCost = new JTable();
		tableCollectedYourCost.setDefaultEditor(Object.class, null);
		scrollPaneYourCost.setViewportView(tableCollectedYourCost);
		tableCollectedYourCost.setModel(dtmYourCost);
		
		JScrollPane scrollPaneMachinePrepTime = new JScrollPane();
		scrollPaneMachinePrepTime.setBounds(493, 493, 100, 122);
		contentPane.add(scrollPaneMachinePrepTime);
		
		tableCollectedPrepTime = new JTable();
		tableCollectedPrepTime.setDefaultEditor(Object.class, null);
		scrollPaneMachinePrepTime.setViewportView(tableCollectedPrepTime);
		tableCollectedPrepTime.setModel(dtmPrepTime);
		
		JScrollPane scrollPaneCollectedOperationTime = new JScrollPane();
		scrollPaneCollectedOperationTime.setBounds(639, 493, 91, 122);
		contentPane.add(scrollPaneCollectedOperationTime);
		
		tableCollectedOperationTime = new JTable();
		tableCollectedOperationTime.setDefaultEditor(Object.class, null);
		scrollPaneCollectedOperationTime.setViewportView(tableCollectedOperationTime);
		tableCollectedOperationTime.setModel(dtmOperationTime);
	}

	private void buttons() 
	{
		JButton btnImport = new JButton("Import");
		btnImport.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				System.out.println("Import");

			}
		});
		btnImport.setBounds(995, 38, 89, 23);
		contentPane.add(btnImport);

		JButton btnPrint = new JButton("Skriv ut");
		btnPrint.setBounds(1171, 625, 89, 23);
		contentPane.add(btnPrint);

		JButton btnSave = new JButton("Spara");
		btnSave.setBounds(1171, 595, 89, 23);
		contentPane.add(btnSave);

		JButton btnAddMachine = new JButton("L\u00E4ggtill");
		btnAddMachine.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				NewOverValue();
				Object[] newRowMachineData = {"Nr","Maskin",startWalue,startAmount,startWalue,startWalue,startWalue};
				//TODO
				
				Object[] newRowMachineCostData = {startWalue};
				
				dtmMachine.addRow(newRowMachineData);
				dtmMachineCost.addRow(newRowMachineCostData);
				dtmPrepTime.addRow(newRowMachineCostData);
				dtmOperationTime.addRow(newRowMachineCostData);
			}
		});
		btnAddMachine.setBounds(66, 81, 89, 23);
		contentPane.add(btnAddMachine);
		
		JButton btnDeliteMachin = new JButton("Ta Bort");
		btnDeliteMachin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int[] rows = Machinetable.getSelectedRows();

				for(int i=0;i<rows.length;i++)
				{
					dtmMachine.removeRow(rows[i]-i);
					dtmMachineCost.removeRow(rows[i]-i);
					//tid
					//tid
				}
			}
		});
		btnDeliteMachin.setBounds(165, 81, 89, 23);
		contentPane.add(btnDeliteMachin);

		JButton btnAddMaterel = new JButton("L\u00E4ggtill");
		btnAddMaterel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewOverValue();
				Object[] newRowMaterialData = {"Matrial",startWalue,overAmount,overMo,overAffo,overVinst,startWalue};
				Object[] newRowMaterialCostData = {startWalue};
				dtmMarieial.addRow(newRowMaterialData);
				dtmMarieialCost.addRow(newRowMaterialCostData);
			}
		});
		btnAddMaterel.setBounds(66, 199, 89, 23);
		contentPane.add(btnAddMaterel);

		JButton btnAddShippingCost = new JButton("L\u00E4ggtill");
		btnAddShippingCost.setBounds(789, 550, 89, 23);
		contentPane.add(btnAddShippingCost);

		JButton btnAddToYourCost = new JButton("L\u00E4ggtill");
		btnAddToYourCost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewOverValue();
				Object[] newRowYourData = { "Vad", startWalue, startWalue, overAmount ,overLo, overAffo,overVinst,startWalue};
				Object[] newRowYourCostData = {startWalue};
				dtmYour.addRow(newRowYourData);
				dtmYourCost.addRow(newRowYourCostData);
			}
		});
		btnAddToYourCost.setBounds(98, 320, 89, 23);
		contentPane.add(btnAddToYourCost);

		JButton btnDeliteMaterial = new JButton("Ta Bort");
		btnDeliteMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				DeliteTabelPoste(Matrialtable, dtmMarieial, dtmMarieialCost);
				//DefaultTableModel model = (DefaultTableModel) this.Matrialtable.getModel();
				
			}
		});
		btnDeliteMaterial.setBounds(165, 199, 89, 23);
		contentPane.add(btnDeliteMaterial);

		JButton btnDeliteYourCost = new JButton("Ta Bort");
		btnDeliteYourCost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//DefaultTableModel model = (DefaultTableModel) this.Matrialtable.getModel();
				DeliteTabelPoste(Yourtable, dtmYour, dtmYourCost);
			}
		});
		btnDeliteYourCost.setBounds(203, 320, 89, 23);
		contentPane.add(btnDeliteYourCost);
	}

	private void label() 
	{

		JLabel lblUnitAmaunt = new JLabel("Syck summan ex moms");
		lblUnitAmaunt.setBounds(1020, 451, 150, 14);
		contentPane.add(lblUnitAmaunt);

		JLabel lblNewYourCost = new JLabel("Era kostnader");
		lblNewYourCost.setBounds(10, 324, 80, 14);
		contentPane.add(lblNewYourCost);

		JLabel lblMachineMaterial = new JLabel("Matrial");
		lblMachineMaterial.setBounds(10, 203, 46, 14);
		contentPane.add(lblMachineMaterial);

		JLabel lblTotalTid = new JLabel("Totala tid");
		lblTotalTid.setBounds(792, 599, 56, 14);
		contentPane.add(lblTotalTid);

		JLabel lblShipping = new JLabel("Frakt kostnad");
		lblShipping.setBounds(789, 500, 80, 14);
		contentPane.add(lblShipping);

		JLabel lblMachine = new JLabel("Maskin");
		lblMachine.setBounds(10, 85, 50, 14);
		contentPane.add(lblMachine);

		JLabel lblMo = new JLabel("MO");
		lblMo.setBounds(436, 42, 30, 14);
		contentPane.add(lblMo);

		JLabel lblDatum_1 = new JLabel("Datum");
		lblDatum_1.setBounds(1118, 510, 46, 14);
		contentPane.add(lblDatum_1);

		JLabel lblTotalAmount = new JLabel("Totala summan ex moms");
		lblTotalAmount.setBounds(1020, 479, 150, 14);
		contentPane.add(lblTotalAmount);

		JLabel lblLo = new JLabel("LO");
		lblLo.setBounds(542, 42, 30, 14);
		contentPane.add(lblLo);

		JLabel lblAffo = new JLabel("Affo");
		lblAffo.setBounds(639, 42, 30, 14);
		contentPane.add(lblAffo);

		JLabel lblCostemerName = new JLabel("Kundens namn");
		lblCostemerName.setBounds(10, 11, 100, 14);
		contentPane.add(lblCostemerName);

		JLabel lblDatum = new JLabel("Datum");
		lblDatum.setBounds(1131, 11, 46, 14);
		contentPane.add(lblDatum);

		JLabel lblProduce = new JLabel("Vad ska Tillverkas");
		lblProduce.setBounds(10, 42, 120, 14);
		contentPane.add(lblProduce);

		JLabel lblAmount = new JLabel("Antal");
		lblAmount.setBounds(229, 42, 46, 14);
		contentPane.add(lblAmount);

		JLabel lblVinst = new JLabel("Vinst");
		lblVinst.setBounds(732, 42, 30, 14);
		contentPane.add(lblVinst);
		
	
	}

	private void textfelds() 
	{
		textFieldDatum = new JTextField();
		textFieldDatum.setBounds(1177, 8, 86, 20);
		contentPane.add(textFieldDatum);
		textFieldDatum.setColumns(10);

		textProduce = new JTextField();
		textProduce.setBounds(124, 39, 95, 20);
		contentPane.add(textProduce);
		textProduce.setColumns(10);

		textAmount = new JTextField();
		textAmount.setBounds(264, 39, 86, 20);
		contentPane.add(textAmount);
		textAmount.setColumns(10);

		textVinst = new JTextField();
		textVinst.setBounds(764, 39, 30, 20);
		contentPane.add(textVinst);
		textVinst.setColumns(10);

		textAffo = new JTextField();
		textAffo.setBounds(668, 39, 30, 20);
		contentPane.add(textAffo);
		textAffo.setColumns(10);

		textLo = new JTextField();
		textLo.setBounds(572, 39, 30, 20);
		contentPane.add(textLo);
		textLo.setColumns(10);

		textMo = new JTextField();
		textMo.setBounds(476, 39, 30, 20);
		contentPane.add(textMo);
		textMo.setColumns(10);

		textImport = new JTextField();
		textImport.setBounds(995, 8, 86, 20);
		contentPane.add(textImport);
		textImport.setColumns(10);

		textDate = new JTextField();
		textDate.setBounds(1174, 507, 86, 20);
		contentPane.add(textDate);
		textDate.setColumns(10);		

		textTotalAmount = new JTextField();
		textTotalAmount.setBounds(1174, 476, 86, 20);
		contentPane.add(textTotalAmount);
		textTotalAmount.setColumns(10);

		textCostemerName = new JTextField();
		textCostemerName.setBounds(99, 8, 120, 20);
		contentPane.add(textCostemerName);
		textCostemerName.setColumns(10);

		textTotalMaterialCost = new JTextField();
		textTotalMaterialCost.setBounds(201, 626, 106, 20);
		contentPane.add(textTotalMaterialCost);
		textTotalMaterialCost.setColumns(10);

		textTotalMachineCost = new JTextField();
		textTotalMachineCost.setBounds(55, 626, 100, 20);
		contentPane.add(textTotalMachineCost);
		textTotalMachineCost.setColumns(10);

		textYourTotalCost = new JTextField();
		textYourTotalCost.setBounds(347, 626, 100, 20);
		contentPane.add(textYourTotalCost);
		textYourTotalCost.setColumns(10);

		textTotalPrepareTime = new JTextField();
		textTotalPrepareTime.setBounds(493, 626, 100, 20);
		contentPane.add(textTotalPrepareTime);
		textTotalPrepareTime.setColumns(10);

		textTotalMachineTime = new JTextField();
		textTotalMachineTime.setBounds(639, 626, 91, 20);
		contentPane.add(textTotalMachineTime);
		textTotalMachineTime.setColumns(10);

		textShippingCost = new JTextField();
		textShippingCost.setBounds(789, 519, 86, 20);
		contentPane.add(textShippingCost);
		textShippingCost.setColumns(10);

		textFieldUnitAmaunt = new JTextField();
		textFieldUnitAmaunt.setColumns(10);
		textFieldUnitAmaunt.setBounds(1174, 448, 86, 20);
		contentPane.add(textFieldUnitAmaunt);

		textTotalTime = new JTextField();
		textTotalTime.setBounds(792, 626, 86, 20);
		contentPane.add(textTotalTime);
		textTotalTime.setColumns(10);
	}


	

	private static void setDate() 
	{
		Date dNow = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat ("dd.MM.yyyy");
		textDate.setText(sdf.format(dNow));
		textFieldDatum.setText(sdf.format(dNow));

	}

	private void setStartProcentWalue()
	{
		textMo.setText(startMo);
		textLo.setText(startLo);
		textAffo.setText(startAffo);
		textVinst.setText(startVinst);
	}

	private void setStarWalue()
	{
		textAmount.setText(startAmount);
		textTotalMachineCost.setText(startWalue);
		textTotalMaterialCost.setText(startWalue);
		textYourTotalCost.setText(startWalue);
		textTotalPrepareTime.setText(startWalue);
		textTotalMachineTime.setText(startWalue);
		textTotalTime.setText(startWalue);
		textShippingCost.setText(startWalue);
		textTotalAmount.setText(startWalue);
		textFieldUnitAmaunt.setText(startWalue);
	}

	public static boolean isDouble( Object input ) {
		try {
			Double.parseDouble( (String) input );
			return true;
		}
		catch( Exception e ) {
			return false;
		}
	}

	private void lisnerMachine()
	{
		dtmMachine.addTableModelListener(new TableModelListener() 
		{
			public void tableChanged(TableModelEvent e)
			{
				TabelListnerValues(Machinetable,e);	
			}
		});
	}
	
	private void lisnerMarial() 
	{
		dtmMarieial.addTableModelListener(new TableModelListener() 
		{
			public void tableChanged(TableModelEvent e)
			{
				TabelListnerValues(Matrialtable,e);	
			}
		});
	}
	
	private void lisnerYour() 
	{
		dtmYour.addTableModelListener(new TableModelListener() 
		{
			public void tableChanged(TableModelEvent e)
			{
				TabelListnerValues(Yourtable,e);			
			}
		});
	}
	
	private static void Materialmathematics(int row, int cunt)
	{	
		int awnser = 6;
		double pris = 0;
		double st = 0;
		double mo = 0;
		double affo = 0;
		double vinst = 0;
		double total = 0;

		//TODO
		int i =1;
		pris = Double.parseDouble(Matrialtable.getValueAt(row, i)+"");
		st = Double.parseDouble(Matrialtable.getValueAt(row, i+1)+"");
		mo = Double.parseDouble(Matrialtable.getValueAt(row, i+2)+"");
		affo = Double.parseDouble(Matrialtable.getValueAt(row, i+3)+"");
		vinst = Double.parseDouble(Matrialtable.getValueAt(row, i+4)+"");

		mo = changetoprocent(mo);
		affo = changetoprocent(affo);
		vinst = changetoprocent(vinst);

		total = (pris*st)*mo*affo*vinst;
		dtmMarieial.setValueAt(total, row, awnser);
		dtmMarieialCost.setValueAt(total, row, 0);
	}

	private static void Yourmathematics(int row, int cunt) {
		int awnser = 7;
		double stelkostnad = 0;
		int antal = 0;
		double stpris = 0;
		double lo = 0;
		double affo = 0;
		double vinst = 0;
		double total = 0;

		//TODO
		int i =1;
		stelkostnad = Double.parseDouble(Yourtable.getValueAt(row, i)+"");
		stpris = Double.parseDouble(Yourtable.getValueAt(row, i+1)+"");
		antal = Integer.parseInt(Yourtable.getValueAt(row, i+2)+"");
		lo = Double.parseDouble(Yourtable.getValueAt(row, i+3)+"");
		affo = Double.parseDouble(Yourtable.getValueAt(row, i+4)+"");
		vinst = Double.parseDouble(Yourtable.getValueAt(row, i+5)+"");
		
		lo = changetoprocent(lo);
		affo = changetoprocent(affo);
		vinst = changetoprocent(vinst);

		total = ((stelkostnad)+(stpris*antal))*lo*affo*vinst;
		dtmYour.setValueAt(total, row, awnser);
		dtmYourCost.setValueAt(total, row, 0);
	}

	private static void Machinemathematics(int row, int cunt) 
	{
		int awnser = 6;
		double prispertimme = 0;
		int antal = 0;
		double steltid = 0;
		double oprationstid = 0;
		double total = 0;

		//TODO
		int i =2;
		prispertimme = Double.parseDouble(Machinetable.getValueAt(row, i)+"");
		antal = Integer.parseInt(Machinetable.getValueAt(row, i+1)+"");
		steltid = Double.parseDouble(Machinetable.getValueAt(row, i+2)+"");
		oprationstid = Double.parseDouble(Machinetable.getValueAt(row, i+3)+"");
		
		System.out.println(prispertimme);
		System.out.println(antal);
		//steltid = changetime(steltid);
		
		total = (prispertimme*steltid)+(oprationstid*prispertimme)*antal;
		dtmMachine.setValueAt(total, row, awnser);
		dtmMachineCost.setValueAt(total, row, 0);
		dtmPrepTime.setValueAt(steltid, row, 0);
		dtmOperationTime.setValueAt(oprationstid, row, 0);
		
	}
	

	private static double changetime(double x) {
		// TODO Auto-generated method stub
		
		return 0;
	}

	private static double changetoprocent(double x) {
		x = x/100;
		return x = x+1;
		
	}

	private void NewTabelPoste(int i, Object newData)
	{
		String string;
		if( i == 1)
		{
			string = (String) newData;
		}
	}
	
	private void DeliteTabelPoste(JTable x, DefaultTableModel y ,DefaultTableModel z)
	{
		int[] rows = x.getSelectedRows();

		for(int i=0;i<rows.length;i++)
		{
			y.removeRow(rows[i]-i);
			z.removeRow(rows[i]-i);
		}
	}
	
	
	private void SumCost(JTable x, JTextField y) 
	{
		int cunt = x.getRowCount();
		
		double sum = 0 ;
		for(int i = 0; i < cunt ; i++)
		{
			sum = sum + Double.parseDouble(x.getValueAt(0, 0).toString());
			System.out.println(sum);
			y.setText(Double.toString(sum));
		}
		
	}
	
	
	
	private void NewOverValue() 
{
		overMo = textMo.getText();
		overLo = textLo.getText();
		overAffo = textAffo.getText();
		overVinst = textVinst.getText();
		overAmount = textAmount.getText();
	}

	private void AddEx()
	{
		textCostemerName.setText("Exempel företag");
		textProduce.setText("Exempel sak");
	}
	
	private void TabelListnerValues(JTable x, TableModelEvent e)
	{
		int row = e.getLastRow();
		int col = e.getColumn();
		int cunt = x.getColumnCount();

		System.out.println(row);
		System.out.println(col);

		if (col >= 0)
		{
			Object newData = x.getValueAt(row, col);
			System.out.println("New: " + newData.toString());
			if (col >= 0 && col <= 8)
			{
				if(isDouble(newData) == true)
				{	
					if(x == Matrialtable)
					{
						Materialmathematics(row, cunt);
					}
					else if(x == Yourtable)
					{
						Yourmathematics(row, cunt);
					}
					else if(x == Machinetable)
					{
						Machinemathematics(row, cunt);
						
					}
				}
					
			}	
		}	
	}
 private void priseUppdate()
 {
	 double totalacost = 0;
	 double machinecost = Double.parseDouble(textTotalMachineCost.getText());
	 double materialcost = Double.parseDouble(textTotalMaterialCost.getText());
	 double Yourcost = Double.parseDouble(textYourTotalCost.getText());
	 double shipment = Double.parseDouble(textShippingCost.getText());
	 
	 totalacost =machinecost+machinecost+Yourcost+shipment;
	 
 }
	
}


