
import java.awt.Component;
import java.awt.EventQueue;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class Main extends JFrame 
{
	NumberFormat nf = NumberFormat.getInstance();
	private static final DecimalFormat formatter = new DecimalFormat( "#.00" );
	
	private JPanel contentPane;
	private JFrame frame;

	
	JComboBox nr;
	JComboBox Serice;
	  
	
	
	// Start v�rden
	static String startWalue = "0";
	static String startAmount = "1";
	static String startMo = "10";
	static String startLo = "10";
	static String startAffo = "10";
	static String startVinst = "5";
	
	//Nya v�rden
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

	static DecimalFormat df;;
	//Service Table
	static Object rowDataService[][] = {};
	static String columnNamesService[] = { "Nummer", "Maskin", "Pris pre timme", "Antal" ,"St�l tid", "Oprations tid", "Pris" };
	static DefaultTableModel dtmService = new DefaultTableModel(rowDataService, columnNamesService);
	//Matrial Table
	static Double rowDataMatrial[][] = {};
	static String columnNamesMatrial[] = { "Matrial", "Pris/Enhet", "M�ngd", "MO", "Affo", "Vinst", "Pris" };
	static DefaultTableModel dtmMarieial = new DefaultTableModel(rowDataMatrial, columnNamesMatrial);
	//Your Table
	static Object rowDataYour[][] = {};
	static String columnNamesYour[] = { "Era Kostnader", "St�lkostnad", "St Pris", "St","LO", "Affo", "Vinst", "Pris" };
	static DefaultTableModel dtmYour = new DefaultTableModel(rowDataYour, columnNamesYour);
	//Service Tabel cost
	static Object rowDataServiceCost[][] = {};
	static String columnNamesServiceCost[] = { "Maskin Kostnader" };
	static DefaultTableModel dtmServiceCost = new DefaultTableModel(rowDataServiceCost, columnNamesServiceCost);
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
	static String columnNamesPrepTime[] = { "F�rberedelse tid" };
	static DefaultTableModel dtmPrepTime = new DefaultTableModel(rowDataPrepTime, columnNamesPrepTime);
	// OprerationTime Tabel 
	static Object rowDataOperationTime[][] = {};
	static String columnNamesOprerationTime[] = { "Operation tid" };
	static DefaultTableModel dtmOperationTime = new DefaultTableModel(rowDataOperationTime, columnNamesOprerationTime);
	
	//OneServiseCost Tabel
	static Object rowDataOneServiceCost[][] = {};
	static String columnNamesOneServiceCost[] = { "En Service" };
	static DefaultTableModel dtmOneServiceCost = new DefaultTableModel(rowDataOneServiceCost, columnNamesOneServiceCost);
		
	// One YourCost Tabel 
	static Object rowDataOneYourCost[][] = {};
	static String columnNamesOneYourCost[] = { "En Era" };
	static DefaultTableModel dtmOneYourCost = new DefaultTableModel(rowDataOneYourCost, columnNamesOneYourCost);
	
	//Textfelt
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
	private JTextField textTotalServiceCost;
	private JTextField textYourTotalCost;
	private JTextField textTotalPrepareTime;
	private JTextField textTotalOperationTime;
	private JTextField textShippingCost;
	private JTextField textTotalTime;
	private JTextField textUnitAmaunt;

	//JTables
	private static JTable tableCollectedMateralCost;
	private JTable tableCollectedYourCost;
	private static JTable servicetabel;
	private static JTable Matrialtable;
	private static JTable Yourtable;
	private JTable tableCollectedServiceCost;
	private JTable tableCollectedPrepTime;
	private JTable tableCollectedOperationTime;
	private JTable tableOneServiceCost;
	private JTable tableOneYourCost;
	private JTextField textOneServiceCost;
	private JTextField textOneYourCost;

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
		setBounds(0, 0, 1500, 900);// �ndra n�r innan f�rdigg //TODO
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);

		maintables();
		panelMaterial();
		panelServiceLists();
		panelKostnader();
		otherTabels();
		buttons();
		textfelds();
		label();
		lisnerService();
		lisnerMarial();
		lisnerYour();
		lisnerServiceRounder();
		lisnerMarialRounder();
		lisnerYourRounder();
		LisnerPreptime();
		LisnerOperationtime();
		LisnerOneService();
		LisnerOneYour();
		setStarWalue();
		setStartProcentWalue();
		setDate();
		AddEx();
		
	}


	private void maintables() //Skapar huvudtabelerna Servic, Matrial och Era Kostnader
	{
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPaneMasikin = new JScrollPane();
		scrollPaneMasikin.setBounds(10, 127, 980, 65);
		contentPane.add(scrollPaneMasikin);
		
		servicetabel = new JTable();
		scrollPaneMasikin.setViewportView(servicetabel);

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
	
	private void panelServiceLists() //Skappar Service tabelen
	{
		servicetabel.setModel(dtmService);
		Commboboxnr(servicetabel);
		CommboboxService(servicetabel);
		servicetabel.getColumnModel().getColumn(6).setCellRenderer(new DecimalFormatRenderer() );
		dtmService.addTableModelListener(new TableModelListener()
		{
			public void tableChanged(TableModelEvent e) 
			{
				int row = e.getLastRow();
				int col = e.getColumn();
				if (col >= 0)
				{
					Object newData = servicetabel.getValueAt(row, col);
					NewTabelPoste(col, newData);
					
					
				}
			}

			
		});
	}
	
	private void Commboboxnr(JTable tabel) 
	{
		String[] Servicenr = {"111","222","333"};
		JComboBox nr = new JComboBox(Servicenr);
		
		tabel.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(nr));
	}
	private void CommboboxService(JTable tabel) 
	{
		
		String[] ServiceThing = {"Sverv","Fr�s","Karuselsverv"};
		JComboBox Service = new JComboBox(ServiceThing);
		
		tabel.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(Service));
	}

	private void panelMaterial() //Skappar Matrial tabelen
	{
		Matrialtable.setModel(dtmMarieial);
		Matrialtable.getColumnModel().getColumn(6).setCellRenderer(new DecimalFormatRenderer() );
		
		dtmMarieial.addTableModelListener(new TableModelListener()
		{
			public void tableChanged(TableModelEvent e) 
			{
				int row = e.getLastRow();
				int col = e.getColumn();
				if (col >= 0)
				{
					System.out.println("RAD " + row + " COL " + col);
					Object newData = Matrialtable.getValueAt(row, col);
					NewTabelPoste(col, newData);
				}
			}
		});
	}

	private void panelKostnader() // Skappar Kostnader tabelen
	{

		Yourtable.setModel(dtmYour);
		Yourtable.getColumnModel().getColumn(7).setCellRenderer(new DecimalFormatRenderer() );
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
				}
			}
		});
	}

	private void otherTabels() //Skapar de tabeler d�r samanlagda prisen fr�n varige rad hamnar 
	{	
		JScrollPane scrollPaneServiceCost = new JScrollPane();
		scrollPaneServiceCost.setBounds(55, 493, 100, 122);
		contentPane.add(scrollPaneServiceCost);
		
		tableCollectedServiceCost = new JTable();
		tableCollectedServiceCost.setDefaultEditor(Object.class, null);
		scrollPaneServiceCost.setViewportView(tableCollectedServiceCost);
		tableCollectedServiceCost.setModel(dtmServiceCost);
		
		JScrollPane scrollPaneMatreialCost = new JScrollPane();
		scrollPaneMatreialCost.setBounds(299, 493, 106, 122);
		contentPane.add(scrollPaneMatreialCost);
		
		tableCollectedMateralCost = new JTable();
		tableCollectedMateralCost.setDefaultEditor(Object.class, null);
		scrollPaneMatreialCost.setViewportView(tableCollectedMateralCost);
		
		tableCollectedMateralCost.setModel(dtmMarieialCost);
		tableCollectedMateralCost.getColumnModel().getColumn(0).setCellRenderer(new DecimalFormatRenderer() );
		
		JScrollPane scrollPaneYourCost = new JScrollPane();
		scrollPaneYourCost.setBounds(554, 493, 100, 122);
		contentPane.add(scrollPaneYourCost);
		
		tableCollectedYourCost = new JTable();
		tableCollectedYourCost.setDefaultEditor(Object.class, null);
		scrollPaneYourCost.setViewportView(tableCollectedYourCost);
		
		tableCollectedYourCost.setModel(dtmYourCost);
		tableCollectedYourCost.getColumnModel().getColumn(0).setCellRenderer(new DecimalFormatRenderer() );
		
		JScrollPane scrollPaneServicePrepTime = new JScrollPane();
		scrollPaneServicePrepTime.setBounds(685, 493, 100, 122);
		contentPane.add(scrollPaneServicePrepTime);
		
		tableCollectedPrepTime = new JTable();
		tableCollectedPrepTime.setDefaultEditor(Object.class, null);
		scrollPaneServicePrepTime.setViewportView(tableCollectedPrepTime);
		
		tableCollectedPrepTime.setModel(dtmPrepTime);
		tableCollectedPrepTime.getColumnModel().getColumn(0).setCellRenderer(new DecimalFormatRenderer() );
		
		JScrollPane scrollPaneCollectedOperationTime = new JScrollPane();
		scrollPaneCollectedOperationTime.setBounds(827, 493, 91, 122);
		contentPane.add(scrollPaneCollectedOperationTime);
		
		tableCollectedOperationTime = new JTable();
		tableCollectedOperationTime.setDefaultEditor(Object.class, null);
		scrollPaneCollectedOperationTime.setViewportView(tableCollectedOperationTime);
		
		tableCollectedOperationTime.setModel(dtmOperationTime);
		tableCollectedOperationTime.getColumnModel().getColumn(0).setCellRenderer(new DecimalFormatRenderer() );
		
		
		
		JScrollPane scrollPaneOneServiceCost = new JScrollPane();
		scrollPaneOneServiceCost.setBounds(175, 493, 100, 122);
		contentPane.add(scrollPaneOneServiceCost);
		
		tableOneServiceCost = new JTable();
		tableOneServiceCost.setDefaultEditor(Object.class, null);
		scrollPaneOneServiceCost.setViewportView(tableOneServiceCost);
		
		
		tableOneServiceCost.setModel(dtmOneServiceCost);
		tableOneServiceCost.getColumnModel().getColumn(0).setCellRenderer(new DecimalFormatRenderer() );
		
		JScrollPane scrollPaneOneYuorCost = new JScrollPane();
		scrollPaneOneYuorCost.setBounds(425, 493, 100, 122);
		contentPane.add(scrollPaneOneYuorCost);
		
		tableOneYourCost = new JTable();
		tableOneYourCost.setDefaultEditor(Object.class, null);
		scrollPaneOneYuorCost.setViewportView(tableOneYourCost);
		
		tableOneYourCost.setModel(dtmOneYourCost);
		tableOneYourCost.getColumnModel().getColumn(0).setCellRenderer(new DecimalFormatRenderer() );
		
	}

	private void buttons() //Skapar alla knappar
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

		JButton btnAddService = new JButton("L\u00E4ggtill");
		btnAddService.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent arg0) 
			{
				NewOverValue();
				AddService();
			}
		});
		btnAddService.setBounds(66, 81, 89, 23);
		contentPane.add(btnAddService);
		
		JButton btnDeliteMachin = new JButton("Ta Bort");
		btnDeliteMachin.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent arg0) 
			{
				DeliteCostAndTime();
				Sumtime();
			}
		});
		btnDeliteMachin.setBounds(165, 81, 89, 23);
		contentPane.add(btnDeliteMachin);

		JButton btnAddMaterel = new JButton("L\u00E4ggtill");
		btnAddMaterel.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				NewOverValue();
				AddMaterial();
			}
		});
		btnAddMaterel.setBounds(66, 199, 89, 23);
		contentPane.add(btnAddMaterel);

		JButton btnAddShippingCost = new JButton("L\u00E4ggtill");
		btnAddShippingCost.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0) {
				priseUppdate();
			}
		});
		btnAddShippingCost.setBounds(943, 551, 89, 23);
		contentPane.add(btnAddShippingCost);

		JButton btnAddToYourCost = new JButton("L\u00E4ggtill");
		btnAddToYourCost.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				NewOverValue();
				AddToYourCost();
			}
		});
		btnAddToYourCost.setBounds(98, 320, 89, 23);
		contentPane.add(btnAddToYourCost);

		JButton btnDeliteMaterial = new JButton("Ta Bort");
		btnDeliteMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				DeliteTabelPoste(Matrialtable, dtmMarieial, dtmMarieialCost,textTotalMaterialCost);
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
				
				DeliteTabelYourPoste(Yourtable, dtmYour, dtmYourCost,textYourTotalCost,textOneYourCost);
				priseUppdate();
			}
		});
		btnDeliteYourCost.setBounds(203, 320, 89, 23);
		contentPane.add(btnDeliteYourCost);
	}

	private void DeliteCostAndTime() 
	{
		// TODO Auto-generated method stub
		int[] rows = servicetabel.getSelectedRows();

		for(int i=0;i<rows.length;i++)
		{
			double m = Double.parseDouble(dtmServiceCost.getValueAt(rows[i], 0)+"");
			double p = Double.parseDouble(dtmPrepTime.getValueAt(rows[i], 0)+"");
			double o = Double.parseDouble(dtmOperationTime.getValueAt(rows[i], 0)+"");
			dtmService.removeRow(rows[i]-i);
			dtmServiceCost.removeRow(rows[i]-i);
			dtmPrepTime.removeRow(rows[i]-i);
			dtmOperationTime.removeRow(rows[i]-i);
			DeliteCostFromSum(m,textTotalServiceCost);
			DeliteCostFromSum(p,textTotalPrepareTime);
			DeliteCostFromSum(o,textTotalOperationTime);
			DeliteSimtime(p,o,textTotalTime);
			Sumtime();
			
		}
	}
	
	private void DeliteSimtime(double p, double o, JTextField x) {
		// TODO Auto-generated method stub
		double q = Double.parseDouble(x.getText());
		double tot = q-(p+o);
		x.setText(Double.toString(tot));
	}

	private void DeliteCostFromSum(double sum, JTextField x) 
	{
		// TODO Auto-generated method stub
		
		//double o = Double.parseDouble(x.getText());
		double o = 0;
		try {
			o = nf.parse(x.getText()).doubleValue();
		} catch (ParseException e) {}
		
		double tot = o-sum;
		
		x.setText(Double.toString(tot));
	}
	
	private void label() 
	{
		
		textOneServiceCost = new JTextField();
		textOneServiceCost.setBounds(175, 626, 100, 20);
		contentPane.add(textOneServiceCost);
		textOneServiceCost.setColumns(10);
		
		textOneYourCost = new JTextField();
		textOneYourCost.setBounds(425, 626, 100, 20);
		contentPane.add(textOneYourCost);
		textOneYourCost.setColumns(10);

		JLabel lblUnitAmaunt = new JLabel("Syck summan ex moms");
		lblUnitAmaunt.setBounds(1020, 451, 150, 14);
		contentPane.add(lblUnitAmaunt);

		JLabel lblNewYourCost = new JLabel("Era kostnader");
		lblNewYourCost.setBounds(10, 324, 80, 14);
		contentPane.add(lblNewYourCost);

		JLabel lblMaterial = new JLabel("Matrial");
		lblMaterial.setBounds(10, 203, 46, 14);
		contentPane.add(lblMaterial);

		JLabel lblTotalTid = new JLabel("Totala tid");
		lblTotalTid.setBounds(944, 601, 56, 14);
		contentPane.add(lblTotalTid);

		JLabel lblShipping = new JLabel("Frakt kostnad");
		lblShipping.setBounds(943, 500, 80, 14);
		contentPane.add(lblShipping);

		JLabel lblService = new JLabel("Service");
		lblService.setBounds(10, 85, 50, 14);
		contentPane.add(lblService);

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
		textTotalMaterialCost.setBounds(299, 626, 106, 20);
		contentPane.add(textTotalMaterialCost);
		textTotalMaterialCost.setColumns(10);

		textTotalServiceCost = new JTextField();
		textTotalServiceCost.setBounds(55, 626, 100, 20);
		contentPane.add(textTotalServiceCost);
		textTotalServiceCost.setColumns(10);

		textYourTotalCost = new JTextField();
		textYourTotalCost.setBounds(554, 626, 100, 20);
		contentPane.add(textYourTotalCost);
		textYourTotalCost.setColumns(10);

		textTotalPrepareTime = new JTextField();
		textTotalPrepareTime.setBounds(685, 626, 100, 20);
		contentPane.add(textTotalPrepareTime);
		textTotalPrepareTime.setColumns(10);

		textTotalOperationTime = new JTextField();
		textTotalOperationTime.setBounds(827, 626, 91, 20);
		contentPane.add(textTotalOperationTime);
		textTotalOperationTime.setColumns(10);

		textShippingCost = new JTextField();
		textShippingCost.setBounds(943, 520, 86, 20);
		contentPane.add(textShippingCost);
		textShippingCost.setColumns(10);

		textUnitAmaunt = new JTextField();
		textUnitAmaunt.setColumns(10);
		textUnitAmaunt.setBounds(1174, 448, 86, 20);
		contentPane.add(textUnitAmaunt);

		textTotalTime = new JTextField();
		textTotalTime.setBounds(943, 626, 86, 20);
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
		textTotalServiceCost.setText(startWalue);
		textTotalMaterialCost.setText(startWalue);
		textYourTotalCost.setText(startWalue);
		textTotalPrepareTime.setText(startWalue);
		textTotalOperationTime.setText(startWalue);
		textTotalTime.setText(startWalue);
		textShippingCost.setText(startWalue);
		textTotalAmount.setText(startWalue);
		textUnitAmaunt.setText(startWalue);
		textOneServiceCost.setText(startWalue);
		textOneYourCost.setText(startWalue);
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

	private void lisnerService()
	{
		dtmService.addTableModelListener(new TableModelListener() 
		{
			public void tableChanged(TableModelEvent e)
			{
				TabelListnerValues(servicetabel,e);
				priseUppdate();
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
				priseUppdate();
			}
		});
	}
	
	private void lisnerServiceRounder() 
	{
		dtmServiceCost.addTableModelListener(new TableModelListener() 
		{
			public void tableChanged(TableModelEvent e)
			{

				SumCost(tableCollectedServiceCost,textTotalServiceCost);
				priseUppdate();
			}
		});
	}
	
	private void lisnerMarialRounder() 
	{
		dtmMarieialCost.addTableModelListener(new TableModelListener() 
		{
			public void tableChanged(TableModelEvent e)
			{
				SumCost(tableCollectedMateralCost, textTotalMaterialCost);
				priseUppdate();
			}
		});
	}

	private void lisnerYourRounder() 
	{
		dtmYourCost.addTableModelListener(new TableModelListener() 
		{
			public void tableChanged(TableModelEvent e)
			{

				SumCost(tableCollectedYourCost,textYourTotalCost);
				priseUppdate();
			}
		});
	}
	
	private void LisnerPreptime() 
	{
		dtmPrepTime.addTableModelListener(new TableModelListener() 
		{
			public void tableChanged(TableModelEvent e)
			{

				SumCost(tableCollectedPrepTime,textTotalPrepareTime);
				Sumtime();
				
			}
		});
	}
	
	private void LisnerOperationtime() 
	{
		dtmOperationTime.addTableModelListener(new TableModelListener() 
		{
			public void tableChanged(TableModelEvent e)
			{
				SumCost(tableCollectedOperationTime,textTotalOperationTime);
				Sumtime();
				
			}
		});
	}
	
	private void LisnerOneService() 
	{
		dtmOneServiceCost.addTableModelListener(new TableModelListener() 
		{
			public void tableChanged(TableModelEvent e)
			{
				
				SumCost(tableOneServiceCost,textOneServiceCost);
				priseUppdate();
			}
		});
	}
	private void LisnerOneYour() 
	{
		dtmOneYourCost.addTableModelListener(new TableModelListener() 
		{
			public void tableChanged(TableModelEvent e)
			{
				
				SumCost(tableOneServiceCost,textOneServiceCost);
				priseUppdate();
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
		double onecost = 0;
		int one = 1;

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
		onecost =((stelkostnad)+(stpris*one))*lo*affo*vinst;
		dtmYour.setValueAt(total, row, awnser);
		dtmYourCost.setValueAt( total, row, 0);
		dtmOneYourCost.setValueAt(onecost, row, 0);
	}

	private static void ServiceMathematics(int row, int cunt) 
	{
		int awnser = 6;
		double prispertimme = 0;
		int antal = 0;
		double steltid = 0;
		double oprationstid = 0;
		double total = 0;
		double onecost = 0;

		//TODO
		int i =2;
		prispertimme = Double.parseDouble(servicetabel.getValueAt(row, i)+"");
		antal = Integer.parseInt(servicetabel.getValueAt(row, i+1)+"");
		steltid = Double.parseDouble(servicetabel.getValueAt(row, i+2)+"");
		oprationstid = Double.parseDouble(servicetabel.getValueAt(row, i+3)+"");
		
		System.out.println(prispertimme);
		System.out.println(antal);
		//steltid = changetime(steltid);
		
		total = ((prispertimme*(steltid/antal))+((oprationstid*prispertimme)*antal));
		onecost = ((prispertimme*steltid)+(oprationstid*prispertimme));
		dtmService.setValueAt(total, row, awnser);
		dtmServiceCost.setValueAt(total, row, 0);
		dtmPrepTime.setValueAt(steltid, row, 0);
		dtmOperationTime.setValueAt(oprationstid, row, 0);
		dtmOneServiceCost.setValueAt(onecost, row, 0);
		
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
	
	private void DeliteTabelPoste(JTable x, DefaultTableModel y ,DefaultTableModel z, JTextField t)
	{
		int[] rows = x.getSelectedRows();
		for(int i=0;i<rows.length;i++)
		{
			
			double r = Double.parseDouble(z.getValueAt(rows[i], 0)+"");
			y.removeRow(rows[i]-i);
			z.removeRow(rows[i]-i);
			DeliteCostFromSum(r,t);
			
		}
		
	}
	private void DeliteTabelYourPoste(JTable x, DefaultTableModel y ,DefaultTableModel z, JTextField t,JTextField u)
	{
		int[] rows = x.getSelectedRows();
		for(int i=0;i<rows.length;i++)
		{
			
			double r = Double.parseDouble(z.getValueAt(rows[i], 0)+"");
			y.removeRow(rows[i]-i);
			z.removeRow(rows[i]-i);
			DeliteCostFromSum(r,t);
			DeliteCostFromSum(r,u);
		}
		
	}
	
	
	
	private void SumCost(JTable x, JTextField y) 
	{
		NumberFormat nf = NumberFormat.getInstance();
		
		int cunt = x.getRowCount();
		
		double sum = 0 ;
		for(int i = 0; i < cunt ; i++)
		{
			String s = x.getValueAt(i, 0).toString();
			Double d = Double.parseDouble(s);
			
			if (d > 0)
			{
				s = String.format("%.2f", d);
				System.out.println("STRING: " + s);
				
				try {
					d = nf.parse(s).doubleValue();
				} catch (ParseException e) {}
			}
			
			sum = sum + d;
			
			System.out.println("VARDE: " + d + " SUMMA: " + sum);
			
			if (sum == 0)
			{
				sum = 0.00;
			}
			
			y.setText(String.format("%.2f", sum));
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
		textCostemerName.setText("Exempel f�retag");
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
			if (col >= 0 && col <=cunt)
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
					else if(x == servicetabel)
					{
						ServiceMathematics(row, cunt);
					}
				}
					
			}	
		}
	}
	
	private void priseUppdate()
	{
		double totalacost = 0;
		double ServiceCost = 0;
		double materialcost = 0;
		double Yourcost = 0;
		double OneService = 0;
		double OneYour = 0;
		
		ServiceCost = symbolchanger(textTotalServiceCost);
		materialcost = symbolchanger(textTotalMaterialCost);
		Yourcost = symbolchanger(textYourTotalCost);
		OneService = symbolchanger(textOneServiceCost);
		OneYour = symbolchanger(textOneYourCost);
		double shipment = Double.parseDouble(textShippingCost.getText());
		
		totalacost =ServiceCost+materialcost+Yourcost+shipment+OneService+OneYour;
		
		String tc = String.format("%.2f", totalacost);
		textTotalAmount.setText(tc);
	}
	
	private void Sumtime()
	{
		double totalacost = 0;
		double preptime = 0;
		double operationtime = 0;
		preptime = symbolchanger(textTotalPrepareTime);
		operationtime = symbolchanger(textTotalOperationTime);
		
		totalacost = preptime + operationtime;
		System.out.println(totalacost);
		textTotalTime.setText(String.format("%.2f", totalacost));

	}
	
	private void AddService() 
	{
		Object[] newRowServiceData = {"Nr","Service",startWalue,startAmount,startWalue,startWalue,startWalue};
		Object[] newRowServiceCostData = {startWalue};
		dtmService.addRow(newRowServiceData);
		dtmServiceCost.addRow(newRowServiceCostData);
		dtmPrepTime.addRow(newRowServiceCostData);
		dtmOperationTime.addRow(newRowServiceCostData);
		dtmOneServiceCost.addRow(newRowServiceCostData);
	}
	
	private void AddMaterial() 
	{
		Object[] newRowMaterialData = {"Matrial",startWalue,overAmount,overMo,overAffo,overVinst,startWalue};
		Object[] newRowMaterialCostData = {startWalue};
		dtmMarieial.addRow(newRowMaterialData);
		dtmMarieialCost.addRow(newRowMaterialCostData);
	}
	
	private void AddToYourCost()
	{
		Object[] newRowYourData = { "Vad", startWalue, startWalue, overAmount ,overLo, overAffo,overVinst,startWalue};
		Object[] newRowYourCostData = {startWalue};
		dtmYour.addRow(newRowYourData);
		dtmYourCost.addRow(newRowYourCostData);
		dtmOneYourCost.addRow(newRowYourCostData);
	}
	
	private  double symbolchanger(JTextField tf)
	{
		double i= 0; 
		try {
			i = nf.parse(tf.getText()).doubleValue();
		} catch (ParseException e) {}
		return i;
		
	}
	
	
	static class DecimalFormatRenderer extends DefaultTableCellRenderer 
	{   
	      public Component getTableCellRendererComponent(
	         JTable table, Object value, boolean isSelected,
	         boolean hasFocus, int row, int column) {
	 
	    	 Object number = null;
	    	  if (value instanceof Double)
	    	  {
	    		  number = formatter.format((Number)value);
	    	  }
	    	  else if (value instanceof String)
	    	  {
	    		  Double val = Double.parseDouble((String)value);
	    		  number = formatter.format((Number)val);
	    	  }
	    	  
	         return super.getTableCellRendererComponent(
	            table, number, isSelected, hasFocus, row, column );
	      }
	   }
}


