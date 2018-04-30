
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FileDialog;
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
import javax.swing.JFileChooser;
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
import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.pdmodel.font.PDCIDFont;
import org.apache.pdfbox.pdmodel.font.PDCIDFontType0;
import org.apache.pdfbox.pdmodel.font.PDCIDFontType2;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1CFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.PDType3Font;
import org.apache.pdfbox.pdmodel.graphics.PDFontSetting;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.util.NumberFormatUtil;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class Main extends JFrame 
{
	NumberFormat nf = NumberFormat.getInstance();
	private static final DecimalFormat formatter = new DecimalFormat( "#.00" );

	private JPanel contentPane;
	private JFrame frame;

	JComboBox nr;
	JComboBox Serice;
	
	// Start värden och standarvärden
	static String startWalue = "0";
	static String startAmount = "1";
	static String startMo = "10";
	static String startLo = "10";
	static String startAffo = "10";
	static String startVinst = "5";

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
	
	
	//JONAS---
	final FileDialog fc = new FileDialog(this, "Öppna", FileDialog.LOAD);
	
	ArrayList<Service> services = new ArrayList<>();
	//--------

	static DecimalFormat df;;
	//Service Table
	static Object rowDataService[][] = {};
	static String columnNamesService[] = { "Nummer", "Service", "Pris pre timme", "Antal" ,"Stäl tid", "Oprations tid", "Pris" };
	static DefaultTableModel dtmService = new DefaultTableModel(rowDataService, columnNamesService);
	//Matrial Table
	static Double rowDataMatrial[][] = {};
	static String columnNamesMatrial[] = { "Matrial", "Pris/Enhet", "Mängd", "MO", "Affo", "Vinst", "Pris" };
	static DefaultTableModel dtmMarieial = new DefaultTableModel(rowDataMatrial, columnNamesMatrial);
	//Your Table
	static Object rowDataYour[][] = {};
	static String columnNamesYour[] = { "Era Kostnader", "Stälkostnad", "St Pris", "St","LO", "Affo", "Vinst", "Pris" };
	static DefaultTableModel dtmYour = new DefaultTableModel(rowDataYour, columnNamesYour);
	//Service Tabel cost
	static Object rowDataServiceCost[][] = {};
	static String columnNamesServiceCost[] = { "Service kostnader" };
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
	static String columnNamesPrepTime[] = { "Förberedelse tid" };
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
	private JTextField textAmuntDivided;
	private JTextField textPrislista;

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
		setBounds(0, 0, 1920, 1080);
		setVisible(true);
		
		//JONAS---
		fc.setFile("*.pdf");
		fc.setTitle("Importera fil (PDF-fil)");
		fc.setDirectory(null);
		//--------

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
		lisnerPreptimeRunder();
		lisnerOperationtimeRounder();
		lisnerOneServiceRounder();
		lisnerOneYourRounder();
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

		JScrollPane scrollPaneService = new JScrollPane();
		scrollPaneService.setBounds(10, 127, 1884, 140);
		contentPane.add(scrollPaneService);

		servicetabel = new JTable();
		scrollPaneService.setViewportView(servicetabel);

		JScrollPane scrollPaneMatrial = new JScrollPane();
		scrollPaneMatrial.setBounds(10, 332, 1884, 140);
		contentPane.add(scrollPaneMatrial);

		Matrialtable = new JTable();

		scrollPaneMatrial.setViewportView(Matrialtable);
		scrollPaneMatrial.setVisible(true);

		JScrollPane scrollPaneKostnader = new JScrollPane();
		scrollPaneKostnader.setBounds(10, 537, 1884, 140);
		contentPane.add(scrollPaneKostnader);

		Yourtable = new JTable();
		scrollPaneKostnader.setViewportView(Yourtable);
		scrollPaneKostnader.setVisible(true);
	}

	private void panelServiceLists() //Skappar Service tabelen
	{
		servicetabel.setModel(dtmService);
		//Jonas-------------
		//Commboboxnr(servicetabel);
		//CommboboxService(servicetabel);
		//--------------------
		servicetabel.getColumnModel().getColumn(6).setCellRenderer(new DecimalFormatRenderer() );
		dtmService.addTableModelListener(new TableModelListener()
		{
			public void tableChanged(TableModelEvent e) 
			{
				//System.out.println("HEJ");
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

	private void Commboboxnr(JTable tabel)//Sakapar comboboxsarna i tabelen 
	{
		//Jonas-----------
		ArrayList<Object> Servicenr = new ArrayList<Object>();
		
		for (int i = 0; i < services.size(); i++)
		{
			Servicenr.add(services.get(i).data_.toArray()[0]);
		}
		
		nr = new JComboBox(Servicenr.toArray());
		//---------------
		tabel.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(nr));
		
		//JONAS---------
		nr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = nr.getSelectedIndex();
				
				if (tabel.getSelectedRow() > -1)
				{
					dtmService.setValueAt(services.get(i).data_.toArray()[1], tabel.getSelectedRow(), 1);
					dtmService.setValueAt(services.get(i).data_.toArray()[2], tabel.getSelectedRow(), 2);
				}
			}
		});
		//------------
	}
	
	private void CommboboxService(JTable tabel)//Sakapar comboboxsarna i tabelen 
	{
		//Jonas------------
		ArrayList<Object> ServiceThing = new ArrayList<Object>();
		
		for (int i = 0; i < services.size(); i++)
		{
			ServiceThing.add(services.get(i).data_.toArray()[1]);
		}
		
		Serice = new JComboBox(ServiceThing.toArray());
		//--------------
		tabel.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(Serice));
		
		//JONAS---------
		Serice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = Serice.getSelectedIndex();
				dtmService.setValueAt(services.get(i).data_.toArray()[0], tabel.getSelectedRow(), 0);
				dtmService.setValueAt(services.get(i).data_.toArray()[2], tabel.getSelectedRow(), 2);
			}
		});
		//------------
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
					//System.out.println("RAD " + row + " COL " + col);
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

	private void otherTabels() //Skapar de tabeler där samanlagda prisen från varige rad hamnar 
	{	
		JScrollPane scrollPaneServiceCost = new JScrollPane();
		scrollPaneServiceCost.setBounds(10, 767, 106, 140);
		contentPane.add(scrollPaneServiceCost);

		tableCollectedServiceCost = new JTable();
		tableCollectedServiceCost.setDefaultEditor(Object.class, null);
		scrollPaneServiceCost.setViewportView(tableCollectedServiceCost);
		
		tableCollectedServiceCost.setModel(dtmServiceCost);
		tableCollectedServiceCost.getColumnModel().getColumn(0).setCellRenderer(new DecimalFormatRenderer() );
		
		JScrollPane scrollPaneMatreialCost = new JScrollPane();
		scrollPaneMatreialCost.setBounds(131, 767, 106, 140);
		contentPane.add(scrollPaneMatreialCost);

		tableCollectedMateralCost = new JTable();
		tableCollectedMateralCost.setDefaultEditor(Object.class, null);
		scrollPaneMatreialCost.setViewportView(tableCollectedMateralCost);

		tableCollectedMateralCost.setModel(dtmMarieialCost);
		tableCollectedMateralCost.getColumnModel().getColumn(0).setCellRenderer(new DecimalFormatRenderer() );

		JScrollPane scrollPaneYourCost = new JScrollPane();
		scrollPaneYourCost.setBounds(258, 767, 100, 140);
		contentPane.add(scrollPaneYourCost);

		tableCollectedYourCost = new JTable();
		tableCollectedYourCost.setDefaultEditor(Object.class, null);
		scrollPaneYourCost.setViewportView(tableCollectedYourCost);

		tableCollectedYourCost.setModel(dtmYourCost);
		tableCollectedYourCost.getColumnModel().getColumn(0).setCellRenderer(new DecimalFormatRenderer() );

		JScrollPane scrollPaneServicePrepTime = new JScrollPane();
		scrollPaneServicePrepTime.setBounds(379, 767, 100, 140);
		contentPane.add(scrollPaneServicePrepTime);

		tableCollectedPrepTime = new JTable();
		tableCollectedPrepTime.setDefaultEditor(Object.class, null);
		scrollPaneServicePrepTime.setViewportView(tableCollectedPrepTime);

		tableCollectedPrepTime.setModel(dtmPrepTime);
		tableCollectedPrepTime.getColumnModel().getColumn(0).setCellRenderer(new DecimalFormatRenderer() );

		JScrollPane scrollPaneCollectedOperationTime = new JScrollPane();
		scrollPaneCollectedOperationTime.setBounds(500, 767, 91, 140);
		contentPane.add(scrollPaneCollectedOperationTime);

		tableCollectedOperationTime = new JTable();
		tableCollectedOperationTime.setDefaultEditor(Object.class, null);
		scrollPaneCollectedOperationTime.setViewportView(tableCollectedOperationTime);

		tableCollectedOperationTime.setModel(dtmOperationTime);
		tableCollectedOperationTime.getColumnModel().getColumn(0).setCellRenderer(new DecimalFormatRenderer() );

		JScrollPane scrollPaneOneServiceCost = new JScrollPane();
		scrollPaneOneServiceCost.setBounds(722, 767, 100, 140);
		contentPane.add(scrollPaneOneServiceCost);

		tableOneServiceCost = new JTable();
		tableOneServiceCost.setDefaultEditor(Object.class, null);
		scrollPaneOneServiceCost.setViewportView(tableOneServiceCost);

		tableOneServiceCost.setModel(dtmOneServiceCost);
		tableOneServiceCost.getColumnModel().getColumn(0).setCellRenderer(new DecimalFormatRenderer() );

		JScrollPane scrollPaneOneYuorCost = new JScrollPane();
		scrollPaneOneYuorCost.setBounds(843, 767, 100, 140);
		contentPane.add(scrollPaneOneYuorCost);

		tableOneYourCost = new JTable();
		tableOneYourCost.setDefaultEditor(Object.class, null);
		scrollPaneOneYuorCost.setViewportView(tableOneYourCost);

		tableOneYourCost.setModel(dtmOneYourCost);
		tableOneYourCost.getColumnModel().getColumn(0).setCellRenderer(new DecimalFormatRenderer() );
	}

	private void buttons() //Skapar alla knappar
	{
		JButton btnImport = new JButton("Importera befintlig offert");
		btnImport.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e) 
			{
				//Jonas--------
				fc.setFile("*.pdf");
				fc.setVisible(true);
				//int returnVal = JFileChooser.APPROVE_OPTION;
				String path = fc.getDirectory();
				String filename = path + fc.getFile();
				
				if (fc.getFile() != null)
				{
					//System.out.println(filename);
					textImport.setText(filename);

					try {
						NewOverValue();
						ReadOffer(filename);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				
				//-------------
				
			}
		});
		btnImport.setBounds(951, 41, 178, 23);
		contentPane.add(btnImport);

		JButton btnSave = new JButton("Spara");
		btnSave.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e) 
			{
				//Jonas----------
				try {
					CreateSimplePDF();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				//--------------
			}
		});
		btnSave.setBounds(1787, 931, 89, 23);
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
				ServiceReset();
				Sumtime();
				priseUppdate();
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
		btnAddMaterel.setBounds(66, 288, 89, 23);
		contentPane.add(btnAddMaterel);

		JButton btnAddShippingCost = new JButton("L\u00E4ggtill");
		btnAddShippingCost.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0) {
				priseUppdate();
			}
		});
		btnAddShippingCost.setBounds(612, 821, 89, 23);
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
		btnAddToYourCost.setBounds(99, 493, 89, 23);
		contentPane.add(btnAddToYourCost);

		JButton btnDeliteMaterial = new JButton("Ta Bort");
		btnDeliteMaterial.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				DeliteTabelMaterialPoste(Matrialtable, dtmMarieial, dtmMarieialCost,textTotalMaterialCost);
				MaterialReset();
				priseUppdate();
			}
		});
		btnDeliteMaterial.setBounds(165, 288, 89, 23);
		contentPane.add(btnDeliteMaterial);

		JButton btnDeliteYourCost = new JButton("Ta Bort");
		btnDeliteYourCost.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				DeliteTabelYourPoste(Yourtable, dtmYour, dtmYourCost,dtmOneYourCost,textYourTotalCost,textOneYourCost);
				YourReset();
				priseUppdate();
			}
		});
		btnDeliteYourCost.setBounds(209, 493, 89, 23);
		contentPane.add(btnDeliteYourCost);
		
		//Jonas-----
		JButton btnImporteraPrislista = new JButton("Importera prislista");
		btnImporteraPrislista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fc.setFile("*.pdf");
				fc.setVisible(true);

				String path = fc.getDirectory();
				String filename = path + fc.getFile();
				
				if (fc.getFile() != null)
				{
					//System.out.println(filename);
					textPrislista.setText(filename);

					try {
						NewOverValue();
						ReadPriceList(filename);
						Commboboxnr(servicetabel);
						CommboboxService(servicetabel);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnImporteraPrislista.setBounds(951, 7, 178, 23);
		contentPane.add(btnImporteraPrislista);
		
	}
	
	private void ServiceReset()//När alla tabeler är bortagna ur listan så stätter textfeltvärdena som hörtill till start värde
	{
		if(tableCollectedServiceCost.getRowCount() == 0)
		{
			textTotalServiceCost.setText(startWalue);
			textOneServiceCost.setText(startWalue);
			textTotalPrepareTime.setText(startWalue);
			textTotalOperationTime.setText(startWalue);
			textTotalTime.setText(startWalue);
		}
	}
	
	private void MaterialReset()//När alla tabeler är bortagna ur listan så stätter textfeltvärdena som hörtill till start värde
	{
		if(tableCollectedMateralCost.getRowCount() == 0)
		{
			textTotalMaterialCost.setText(startWalue);
		}
	}

	private void YourReset()//När alla tabeler är bortagna ur listan så stätter textfeltvärdena som hörtill till start värde
	{
		if(tableCollectedYourCost.getRowCount() == 0)
		{
			textOneYourCost.setText(startWalue);
			textYourTotalCost.setText(startWalue);
		}
	}

	private void label()//Skapar alla labels
	{
		JLabel lblUnitAmaunt = new JLabel("Styck summan ex moms");
		lblUnitAmaunt.setBounds(1619, 811, 150, 14);
		contentPane.add(lblUnitAmaunt);

		JLabel lblNewYourCost = new JLabel("Era kostnader");
		lblNewYourCost.setBounds(10, 493, 80, 14);
		contentPane.add(lblNewYourCost);

		JLabel lblMaterial = new JLabel("Matrial");
		lblMaterial.setBounds(10, 292, 46, 14);
		contentPane.add(lblMaterial);

		JLabel lblTotalTid = new JLabel("Totala tid");
		lblTotalTid.setBounds(612, 893, 56, 14);
		contentPane.add(lblTotalTid);

		JLabel lblShipping = new JLabel("Frakt kostnad");
		lblShipping.setBounds(612, 767, 80, 14);
		contentPane.add(lblShipping);

		JLabel lblService = new JLabel("Service");
		lblService.setBounds(10, 85, 50, 14);
		contentPane.add(lblService);

		JLabel lblMo = new JLabel("MO");
		lblMo.setBounds(436, 42, 30, 14);
		contentPane.add(lblMo);

		JLabel lblDatum_1 = new JLabel("Datum");
		lblDatum_1.setBounds(1723, 893, 46, 14);
		contentPane.add(lblDatum_1);

		JLabel lblTotalAmount = new JLabel("Totala summan ex moms");
		lblTotalAmount.setBounds(1619, 852, 150, 14);
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
		lblDatum.setBounds(1720, 11, 46, 14);
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

		JLabel lblUnitAmuntDivided = new JLabel("Styck summa vid flertal best\u00E4lda ex moms");
		lblUnitAmuntDivided.setBounds(1516, 764, 247, 26);
		contentPane.add(lblUnitAmuntDivided);
		
		JLabel lblPricelist = new JLabel("Offert");
		lblPricelist.setBounds(1139, 45, 40, 14);
		contentPane.add(lblPricelist);
		
		
		
		textPrislista = new JTextField();
		textPrislista.setBounds(1189, 8, 335, 20);
		contentPane.add(textPrislista);
		textPrislista.setColumns(10);
		
		JLabel lblPrislista = new JLabel("Prislista");
		lblPrislista.setBounds(1139, 11, 46, 14);
		contentPane.add(lblPrislista);
		
		JLabel lblErlieroffer = new JLabel("Tidigare offert");
		lblErlieroffer.setBounds(1359, 11, 80, 14);
		contentPane.add(lblErlieroffer);
	}

	private void textfelds() //Skapar alla textfelt
	{
		textFieldDatum = new JTextField();
		textFieldDatum.setBounds(1787, 8, 86, 20);
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
		textImport.setBounds(1189, 42, 335, 20);
		contentPane.add(textImport);
		textImport.setColumns(10);

		textDate = new JTextField();
		textDate.setBounds(1790, 890, 86, 20);
		contentPane.add(textDate);
		textDate.setColumns(10);		

		textTotalAmount = new JTextField();
		textTotalAmount.setBounds(1790, 849, 86, 20);
		contentPane.add(textTotalAmount);
		textTotalAmount.setColumns(10);

		textCostemerName = new JTextField();
		textCostemerName.setBounds(99, 8, 120, 20);
		contentPane.add(textCostemerName);
		textCostemerName.setColumns(10);

		textTotalMaterialCost = new JTextField();
		textTotalMaterialCost.setBounds(131, 928, 106, 20);
		contentPane.add(textTotalMaterialCost);
		textTotalMaterialCost.setColumns(10);

		textTotalServiceCost = new JTextField();
		textTotalServiceCost.setBounds(10, 928, 100, 20);
		contentPane.add(textTotalServiceCost);
		textTotalServiceCost.setColumns(10);

		textYourTotalCost = new JTextField();
		textYourTotalCost.setBounds(258, 928, 100, 20);
		contentPane.add(textYourTotalCost);
		textYourTotalCost.setColumns(10);

		textTotalPrepareTime = new JTextField();
		textTotalPrepareTime.setBounds(379, 928, 100, 20);
		contentPane.add(textTotalPrepareTime);
		textTotalPrepareTime.setColumns(10);

		textTotalOperationTime = new JTextField();
		textTotalOperationTime.setBounds(500, 928, 91, 20);
		contentPane.add(textTotalOperationTime);
		textTotalOperationTime.setColumns(10);

		textShippingCost = new JTextField();
		textShippingCost.setBounds(612, 794, 86, 20);
		contentPane.add(textShippingCost);
		textShippingCost.setColumns(10);

		textUnitAmaunt = new JTextField();
		textUnitAmaunt.setColumns(10);
		textUnitAmaunt.setBounds(1790, 808, 86, 20);
		contentPane.add(textUnitAmaunt);

		textTotalTime = new JTextField();
		textTotalTime.setBounds(612, 928, 86, 20);
		contentPane.add(textTotalTime);
		textTotalTime.setColumns(10);

		textOneServiceCost = new JTextField();
		textOneServiceCost.setBounds(722, 928, 100, 20);
		contentPane.add(textOneServiceCost);
		textOneServiceCost.setColumns(10);

		textOneYourCost = new JTextField();
		textOneYourCost.setBounds(843, 928, 100, 20);
		contentPane.add(textOneYourCost);
		textOneYourCost.setColumns(10);

		textAmuntDivided = new JTextField();
		textAmuntDivided.setBounds(1790, 767, 86, 20);
		contentPane.add(textAmuntDivided);
		textAmuntDivided.setColumns(10);
	}

	private static void setDate()//Sätter datumet i textrutan 
	{
		Date dNow = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat ("dd.MM.yyyy");
		textDate.setText(sdf.format(dNow));
		textFieldDatum.setText(sdf.format(dNow));
	}

	private void setStartProcentWalue()//Sätter Prosent startvärden
	{
		textMo.setText(startMo);
		textLo.setText(startLo);
		textAffo.setText(startAffo);
		textVinst.setText(startVinst);
	}

	private void setStarWalue()//Sätter de övriga startvärdena
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
		textAmuntDivided.setText(startWalue);
	}

	public static boolean isDouble( Object input ) //Ser om ett värde är en double
	{
		try 
		{
			Double.parseDouble( (String) input );
			return true;
		}
		catch( Exception e ) 
		{
			return false;
		}
	}

	private void lisnerService()//När Servictabelen samanlagd costnad updateras så uppdateras samanräkningaen av priset 
	{
		dtmService.addTableModelListener(new TableModelListener() 
		{
			public void tableChanged(TableModelEvent e)
			{
				TabelListnerValues(servicetabel,e);
			}
		});
	}

	private void lisnerMarial()//När Matrialtabelen samanlagd costnad updateras så uppdateras samanräkningaen av priset 
	{
		dtmMarieial.addTableModelListener(new TableModelListener() 
		{
			public void tableChanged(TableModelEvent e)
			{
				TabelListnerValues(Matrialtable,e);
			}
		});
	}

	private void lisnerYour()//När Ercostnadtabel samanlagd costnad updateras så uppdateras samanräkningaen av priset 
	{
		dtmYour.addTableModelListener(new TableModelListener() 
		{
			public void tableChanged(TableModelEvent e)
			{
				TabelListnerValues(Yourtable,e);
			}
		});
	}

	private void lisnerServiceRounder() //När Servicetabel samanlagd costnad updateras så avrundar den nya suman
	{
		dtmServiceCost.addTableModelListener(new TableModelListener() 
		{
			public void tableChanged(TableModelEvent e)
			{
				SumRounder(tableCollectedServiceCost,textTotalServiceCost);
				priseUppdate();
			}
		});
	}

	private void lisnerMarialRounder()//När Matrialtabel samanlagd costnad updateras så avrundar den nya suman 
	{
		dtmMarieialCost.addTableModelListener(new TableModelListener() 
		{
			public void tableChanged(TableModelEvent e)
			{
				SumRounder(tableCollectedMateralCost, textTotalMaterialCost);
				priseUppdate();
			}
		});
	}

	private void lisnerYourRounder() //När Eracostnadertabel samanlagd costnad updateras så avrundar den nya suman
	{
		dtmYourCost.addTableModelListener(new TableModelListener() 
		{
			public void tableChanged(TableModelEvent e)
			{
				SumRounder(tableCollectedYourCost,textYourTotalCost);
				priseUppdate();
			}
		});
	}

	private void lisnerPreptimeRunder() //När samanlagda Stältidtabel updateras så avrundar den nya suman
	{
		dtmPrepTime.addTableModelListener(new TableModelListener() 
		{
			public void tableChanged(TableModelEvent e)
			{
				SumRounder(tableCollectedPrepTime,textTotalPrepareTime);
				Sumtime();
			}
		});
	}

	private void lisnerOperationtimeRounder() //När samanlagda Tilverkningstidstidtabel updateras så avrundar den nya suman
	{
		dtmOperationTime.addTableModelListener(new TableModelListener() 
		{
			public void tableChanged(TableModelEvent e)
			{
				SumRounder(tableCollectedOperationTime,textTotalOperationTime);
				Sumtime();
			}
		});
	}

	private void lisnerOneServiceRounder() //När OneService tabelen upbateras så avrundar den nya suman 
	{
		dtmOneServiceCost.addTableModelListener(new TableModelListener() 
		{
			public void tableChanged(TableModelEvent e)
			{
				SumRounder(tableOneServiceCost,textOneServiceCost);
				priseUppdate();
			}
		});
	}
	
	private void lisnerOneYourRounder()//När OneYourcost tabelen upbateras så avrundar den nya suman  
	{
		dtmOneYourCost.addTableModelListener(new TableModelListener() 
		{
			public void tableChanged(TableModelEvent e)
			{
				SumRounder(tableOneYourCost,textOneYourCost);
				priseUppdate();
			}
		});
	}

	private static void Materialmathematics(int row, int cunt) //Räknar utt maten i Matrial tabelen och skriver utt den i tilhörande text ruta
	{	

		int awnser = 6;
		double pris = 0;
		double st = 0;
		double mo = 0;
		double affo = 0;
		double vinst = 0;
		double total = 0;
		int i =1;
		
		pris = Double.parseDouble(Matrialtable.getValueAt(row, i)+"");
		st = Double.parseDouble(Matrialtable.getValueAt(row, i+1)+"");
		mo = Double.parseDouble(Matrialtable.getValueAt(row, i+2)+"");
		affo = Double.parseDouble(Matrialtable.getValueAt(row, i+3)+"");
		vinst = Double.parseDouble(Matrialtable.getValueAt(row, i+4)+"");

		mo = changetoprocent(mo);
		affo = changetoprocent(affo);
		vinst = changetoprocent(vinst);

		if (st == 0)
		{
			total = 0;
		}
		else
		{
			total = (pris*st)*mo*affo*vinst;
		}
		
		dtmMarieial.setValueAt(total, row, awnser);
		dtmMarieialCost.setValueAt(total, row, 0);
	}

	private static void Yourmathematics(int row, int cunt) //Räknar utt maten i Eracostnader tabelen och skriver utt den i tilhörande text ruta
	{
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

		if (antal == 0)
		{
			total = 0;
			onecost = 0;
		}
		else
		{
			total = ((stelkostnad)+(stpris*antal))*lo*affo*vinst;
			onecost =((stelkostnad)+(stpris*one))*lo*affo*vinst;
		}
		
		dtmYour.setValueAt(total, row, awnser);
		dtmYourCost.setValueAt( total, row, 0);
		dtmOneYourCost.setValueAt(onecost, row, 0);
	}

	private static void ServiceMathematics(int row, int cunt) //Räknar utt maten i Service tabelen och skriver utt den i tilhörande text ruta
	{
		int awnser = 6;
		double prispertimme = 0;
		int antal = 0;
		double steltid = 0;
		double oprationstid = 0;
		double total = 0;
		double onecost = 0;
		int i =2;

		prispertimme = Double.parseDouble(servicetabel.getValueAt(row, i)+"");
		antal = Integer.parseInt(servicetabel.getValueAt(row, i+1)+"");
		steltid = Double.parseDouble(servicetabel.getValueAt(row, i+2)+"");
		oprationstid = Double.parseDouble(servicetabel.getValueAt(row, i+3)+"");

		if (antal == 0)
		{
			total = 0;
			onecost = 0;
		}
		else
		{
			total = ((prispertimme*(steltid))+((oprationstid*prispertimme)*antal));
			onecost = ((prispertimme*steltid)+(oprationstid*prispertimme));
		}
		
		dtmService.setValueAt(total, row, awnser);
		dtmServiceCost.setValueAt(total, row, 0);
		dtmPrepTime.setValueAt(steltid, row, 0);
		dtmOperationTime.setValueAt(oprationstid, row, 0);
		dtmOneServiceCost.setValueAt(onecost, row, 0);
	}

	private static double changetoprocent(double x)// Ändrar talet i prosentrutan från text till ett prosental
	{
		x = x/100;
		return x = x+1;
	}

	private void NewTabelPoste(int i, Object newData)//Skapar ny rad i inskikad tabel tabelen 
	{
		String string;
		if( i == 1)
		{
			string = (String) newData;
		}
	}

	private void DeliteCostAndTime()//Tar bort från Servistabelen
	{
		int[] rows = servicetabel.getSelectedRows();
		for(int i=0;i<rows.length;i++)
		{
			dtmService.removeRow(rows[i]-i);
			dtmServiceCost.removeRow(rows[i]-i);
			dtmPrepTime.removeRow(rows[i]-i);
			dtmOperationTime.removeRow(rows[i]-i);
			dtmOneServiceCost.removeRow(rows[i]-i);
			Sumtime();
		}
	}
	
	private void DeliteTabelMaterialPoste(JTable x, DefaultTableModel y ,DefaultTableModel z, JTextField t)//Tar bort rad från Matrial tabelerna
	{
		int[] rows = x.getSelectedRows();
		for(int i=0;i<rows.length;i++)
		{
			y.removeRow(rows[i]-i);
			z.removeRow(rows[i]-i);
		}
	}

	private void DeliteTabelYourPoste(JTable x, DefaultTableModel y ,DefaultTableModel z,DefaultTableModel w, JTextField t,JTextField u)//Tar bort rad från Erankostnad tabelerna
	{
		int[] rows = x.getSelectedRows();
		for(int i=0;i<rows.length;i++)
		{
			y.removeRow(rows[i]-i);
			z.removeRow(rows[i]-i);
			w.removeRow(rows[i]-i);
		}	
	}

	private String SumRounder(String input)
	{
		NumberFormat nf = NumberFormat.getInstance();
		
		Double d = 0.0;
		try {
			d = nf.parse(input).doubleValue();
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		if (d > 0)
		{
			input = String.format("%.2f", d);
			//System.out.println("STRING: " + s);
			try 
			{
				d = nf.parse(input).doubleValue();
			} 
			catch (ParseException e) {}
		}
		else if (d == 0)
		{
			d = 0.00;
		}
		
		return String.format("%.2f", d);
	}
	
	private void SumRounder(JTable x, JTextField y) //Omvandlar suman frå . till , och så den har två desemaler när visas
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
				//System.out.println("STRING: " + s);
				try 
				{
					d = nf.parse(s).doubleValue();
				} 
				catch (ParseException e) {}
			}
			
			sum = sum + d;
			
			//System.out.println("VARDE: " + d + " SUMMA: " + sum);
			
			if (sum == 0)
			{
				sum = 0.00;
			}

			y.setText(String.format("%.2f", sum));
		}

	}

	private void NewOverValue() // Hämtar det nya procentvärdet 
	{
		overMo = textMo.getText();
		overLo = textLo.getText();
		overAffo = textAffo.getText();
		overVinst = textVinst.getText();
		overAmount = textAmount.getText();
	}

	private void AddEx() // Lägger till exempel namn när man startar upp programet
	{
		textCostemerName.setText("Exempel företag");
		textProduce.setText("Exempel sak");
	}

	private void TabelListnerValues(JTable x, TableModelEvent e)//Beroende på vilken tabel som skickas in så väljs var den ska skickas vidare och vilken tabel som ska räknas utt
	{
		int row = e.getLastRow();
		int col = e.getColumn();
		int cunt = x.getColumnCount();

		//System.out.println(row);
		//System.out.println(col);

		if (col >= 0)
		{
			Object newData = x.getValueAt(row, col);
			//System.out.println("New: " + newData.toString());
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

	private void priseUppdate()//Updaterar textfälten total pris, st pris och st pris vid x antal.
	{
		double totalacost = 0;
		double ServiceCost = 0;
		double materialcost = 0;
		double Yourcost = 0;
		double OneService = 0;
		double OneYour = 0;
		double onecost = 0;
		double onestcost = 0;

		ServiceCost = symbolchanger(textTotalServiceCost);
		materialcost = symbolchanger(textTotalMaterialCost);
		Yourcost = symbolchanger(textYourTotalCost);
		OneService = symbolchanger(textOneServiceCost);
		OneYour = symbolchanger(textOneYourCost);
		double shipment = Double.parseDouble(textShippingCost.getText());

		totalacost =ServiceCost+materialcost+Yourcost+shipment;
		onecost = OneService+OneYour+materialcost+shipment;
		
		int unit = Integer.parseInt(textAmount.getText());
		
		if(unit == 0)
		{
			unit = 1;
		}
		onestcost = (totalacost - shipment)/unit + shipment;

		String onest = String.format("%.2f", onestcost);
		String one = String.format("%.2f", onecost);
		String tot = String.format("%.2f", totalacost);

		textAmuntDivided.setText(onest);
		textUnitAmaunt.setText(one);
		textTotalAmount.setText(tot);
	}

	private void Sumtime()//Updaterar textfält total tid och räknar i hop den 
	{
		double totalacost = 0;
		double preptime = 0;
		double operationtime = 0;
		preptime = symbolchanger(textTotalPrepareTime);
		operationtime = symbolchanger(textTotalOperationTime);

		totalacost = preptime + operationtime;
		//System.out.println(totalacost);
		textTotalTime.setText(String.format("%.2f", totalacost));

	}

	private void AddService() //Lägger till ny Servis rad
	{
		int unit = Integer.parseInt(textAmount.getText());
		Object[] newRowServiceData = {"Nr","Service",startWalue,unit,startWalue,startWalue,startWalue};
		Object[] newRowServiceCostData = {startWalue};
		dtmService.addRow(newRowServiceData);
		dtmServiceCost.addRow(newRowServiceCostData);
		dtmPrepTime.addRow(newRowServiceCostData);
		dtmOperationTime.addRow(newRowServiceCostData);
		dtmOneServiceCost.addRow(newRowServiceCostData);
	}
	
	private void AddMaterial() //Lägger till ny Matrial rad
	{
		int unit = Integer.parseInt(textAmount.getText());
		Object[] newRowMaterialData = {"Matrial",startWalue,unit,overMo,overAffo,overVinst,startWalue};
		Object[] newRowMaterialCostData = {startWalue};
		dtmMarieial.addRow(newRowMaterialData);
		dtmMarieialCost.addRow(newRowMaterialCostData);
	}

	private void AddToYourCost()// Läger till ny Era kostnader rad
	{
		int unit = Integer.parseInt(textAmount.getText());
		Object[] newRowYourData = { "Vad", startWalue, startWalue, unit ,overLo, overAffo,overVinst,startWalue};
		Object[] newRowYourCostData = {startWalue};
		dtmYour.addRow(newRowYourData);
		dtmYourCost.addRow(newRowYourCostData);
		dtmOneYourCost.addRow(newRowYourCostData);
	}

	private  double symbolchanger(JTextField tf)//Omvandlar inskickat textfelt
	{
		double i= 0; 
		try {
			i = nf.parse(tf.getText()).doubleValue();
		} catch (ParseException e) {}
		return i;

	}
	
	//JONAS----
	public void ReadOffer(String path) throws Exception
	{
		try (PDDocument document = PDDocument.load(new File(path))) {
 
            document.getClass();

            if (!document.isEncrypted()) {

                //PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                //stripper.setSortByPosition(true);

                PDFTextStripper tStripper = new PDFTextStripper();
                tStripper.setSortByPosition(true);
                
                String pdfFileInText = tStripper.getText(document);
                
                String lines[] = pdfFileInText.split("\\;");
                int counter = 0;
                while (counter < lines.length - 1)
                {
                	String checker = lines[counter].replaceAll("^\\s+", "");
                	counter++;
                	if(checker.compareTo("SERVICE") == 0)
                	{
                		int numServices = Integer.parseInt(lines[counter].replaceAll("^\\s+", ""));
        				ArrayList<Object> temp;
        				counter++;
        				
        				for (int i = 0; i < numServices; i++)
        				{
        					temp = new ArrayList<Object>();
        					for (int j = 0; j < 7; j++)
            				{
        						//System.out.println(lines[counter].replaceAll("^\\s+", ""));
            					temp.add(lines[counter].replaceAll("^\\s+", ""));
            					counter++;
            				}
        					dtmService.addRow(temp.toArray());

        					double prispertimme = Double.parseDouble(temp.get(2)+"");
        					double antal = Integer.parseInt(temp.get(3)+"");
        					double steltid = Double.parseDouble(temp.get(4)+"");
        					double oprationstid = Double.parseDouble(temp.get(5)+"");
        					
        					double total = 0;
        					double onecost = 0;
        					
        					if (antal == 0)
        					{
        						total = 0;
        						onecost = 0;
        					}
        					else
        					{
        						total = ((prispertimme*(steltid))+((oprationstid*prispertimme)*antal));
        						onecost = ((prispertimme*steltid)+(oprationstid*prispertimme));
        					}
        					
        					Object[] temp2 = {total};
        					dtmServiceCost.addRow(temp2);
        					temp2[0] = steltid;
        					dtmPrepTime.addRow(temp2);
        					temp2[0] = oprationstid;
        					dtmOperationTime.addRow(temp2);
        					temp2[0] = onecost;
        					dtmOneServiceCost.addRow(temp2);
        					
        				}
        				servicetabel.setModel(dtmService);
        				
        				//Commboboxnr(servicetabel);
                	}
                	else if(checker.compareTo("MATERIAL") == 0)
                	{
                		int numMaterials = Integer.parseInt(lines[counter].replaceAll("^\\s+", ""));
        				ArrayList<Object> temp;
        				counter++;
        				
        				for (int i = 0; i < numMaterials; i++)
        				{
        					temp = new ArrayList<Object>();
        					for (int j = 0; j < 7; j++)
            				{
        						//System.out.println(lines[counter].replaceAll("^\\s+", ""));
            					temp.add(lines[counter].replaceAll("^\\s+", ""));
            					counter++;
            				}
        					dtmMarieial.addRow(temp.toArray());

        					double pris = Double.parseDouble(temp.get(1)+"");
        					double st = Double.parseDouble(temp.get(2)+"");
        					double mo = Double.parseDouble(temp.get(3)+"");
        					double affo = Double.parseDouble(temp.get(4)+"");
        					double vinst = Double.parseDouble(temp.get(5)+"");
        					double total = 0;
        					
        					mo = changetoprocent(mo);
        					affo = changetoprocent(affo);
        					vinst = changetoprocent(vinst);

        					if (st == 0)
        					{
        						total = 0;
        					}
        					else
        					{
        						total = (pris*st)*mo*affo*vinst;
        					}
        					
        					Object[] temp2 = {total};
        					dtmMarieialCost.addRow(temp2);
        				}
        				Matrialtable.setModel(dtmMarieial);
                	}
                	else if(checker.compareTo("ERA KOSTNADER") == 0)
                	{
                		int numYourCosts = Integer.parseInt(lines[counter].replaceAll("^\\s+", ""));
        				ArrayList<Object> temp;
        				counter++;
        				
        				for (int i = 0; i < numYourCosts; i++)
        				{
        					temp = new ArrayList<Object>();
        					for (int j = 0; j < 8; j++)
            				{
        						//System.out.println(lines[counter].replaceAll("^\\s+", ""));
            					temp.add(lines[counter].replaceAll("^\\s+", ""));
            					counter++;
            				}
        					dtmYour.addRow(temp.toArray());

        					double stelkostnad = 0;
        					int antal = 0;
        					double stpris = 0;
        					double lo = 0;
        					double affo = 0;
        					double vinst = 0;
        					double total = 0;
        					double onecost = 0;
        					int one = 1;

        					stelkostnad = Double.parseDouble(temp.get(1)+"");
        					stpris = Double.parseDouble(temp.get(2)+"");
        					antal = Integer.parseInt(temp.get(3)+"");
        					lo = Double.parseDouble(temp.get(4)+"");
        					affo = Double.parseDouble(temp.get(5)+"");
        					vinst = Double.parseDouble(temp.get(6)+"");

        					lo = changetoprocent(lo);
        					affo = changetoprocent(affo);
        					vinst = changetoprocent(vinst);

        					if (antal == 0)
        					{
        						total = 0;
        						onecost = 0;
        					}
        					else
        					{
        						total = ((stelkostnad)+(stpris*antal))*lo*affo*vinst;
        						onecost =((stelkostnad)+(stpris*one))*lo*affo*vinst;
        					}
        					
        					Object[] temp2 = {total};
        					dtmYourCost.addRow(temp2);
        					temp2[0] = onecost;
        					dtmOneYourCost.addRow(temp2);
        					
        				}
        				Yourtable.setModel(dtmYour);
                	}
                	else if(checker.compareTo("DATUM") == 0)
                	{
                		String date = lines[counter].replaceAll("^\\s+", "");
                		textFieldDatum.setText(date);
                		counter++;
                	}
                	else if(checker.compareTo("KUND") == 0)
                	{
                		String customer = lines[counter].replaceAll("^\\s+", "");
                		textCostemerName.setText(customer);
                		counter++;
                	}
                	else if(checker.compareTo("TILLVERKAS") == 0)
                	{
                		String item = lines[counter].replaceAll("^\\s+", "");
                		textProduce.setText(item);
                		counter++;
                	}
                	else if(checker.compareTo("ANTAL") == 0)
                	{
                		String amount = lines[counter].replaceAll("^\\s+", "");
                		textAmount.setText(amount);
                		counter++;
                	}
                	else if(checker.compareTo("MO LO AFFO VINST") == 0)
                	{
                		String mo = lines[counter].replaceAll("^\\s+", "");
                		textMo.setText(mo);
                		counter++;
                		String lo = lines[counter].replaceAll("^\\s+", "");
                		textLo.setText(lo);
                		counter++;
                		String affo = lines[counter].replaceAll("^\\s+", "");
                		textAffo.setText(affo);
                		counter++;
                		String vinst = lines[counter].replaceAll("^\\s+", "");
                		textVinst.setText(vinst);
                		counter++;
                	}
                }
            }
        }
	}
	
	/*public void ReadPriceList(String path) throws InvalidPasswordException, IOException
	{
		try (PDDocument document = PDDocument.load(new File(path))) {
			 
            document.getClass();

            if (!document.isEncrypted()) {

                //PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                //stripper.setSortByPosition(true);

                PDFTextStripper tStripper = new PDFTextStripper();
                tStripper.setSortByPosition(true);
                
                String pdfFileInText = tStripper.getText(document);
                
                
                String lines[] = pdfFileInText.split("\\;");
                int counter = 0;
                Service s = new Service();
                int status = 0;
                while (counter < lines.length - 1)
                {
                	String checker = lines[counter].replaceAll("^\\s+", "");
                	counter++;
				            	
					if(checker.compareTo("METODER") == 0)
					{
						int numMethods = Integer.parseInt(lines[counter].replaceAll("^\\s+", ""));
						//Tar bort whitespace i början av strängen
				    	
						counter++;
						
						for (int i = 0; i < numMethods; i++)
						{
							s = new Service();
							services.add(s);
							for (int j = 0; j < 4; j++)
							{
								String leftRemoved = lines[counter].replaceAll("^\\s+", "");
								if (leftRemoved.compareTo("") != 0)
								{
									s.AddData(leftRemoved);
									counter++;
								}
							}
						}
					}
                }
            }
		}
	}
	*/
	
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
	
	public void ReadPriceList(String path) throws InvalidPasswordException, IOException
	{
		try (PDDocument document = PDDocument.load(new File(path))) {
			 
            document.getClass();

            if (!document.isEncrypted()) {

                //PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                //stripper.setSortByPosition(true);

                PDFTextStripper tStripper = new PDFTextStripper();
                tStripper.setSortByPosition(true);
                
                String pdfFileInText = tStripper.getText(document);
                
                
                String lines[] = pdfFileInText.split("\\r?\\n");
                int counter = 0;
                Service s = new Service();
                int status = 0;
                while (counter < lines.length - 1)
                {
                	CharSequence H = "kr./tim.";
                	CharSequence Km = "kr./km.";
                	CharSequence D = "kr./dag.";
                	
                	if (lines[counter].contains(H) || lines[counter].contains(Km) || lines[counter].contains(D))
                	{
	                	String nr = lines[counter].substring(0, 3);
	            		nr = nr.trim();
	            		
	            		String service = "";
	            		int start = 4;
	            		if (!isNumeric(nr))
	            		{
	            			start = 0;
	            		}
	            		
	            		CharSequence curSeq = D;
	            		
	                	if (lines[counter].contains(H))
	                	{
	                		curSeq = H;
	                	}
	                	else if (lines[counter].contains(Km))
	                	{
	                		curSeq = Km;	
	                	}
	                	
	                	lines[counter] = lines[counter].replace(curSeq, "");	
	                	lines[counter] = lines[counter].trim();
	                	
	            		
	            		service = lines[counter].substring(start, lines[counter].length() - 4);
	            		service = service.trim();
	            		
	            		String cost = lines[counter].substring(lines[counter].length() - 4, lines[counter].length());
	            		cost = cost.trim();
	        			
	        			//System.out.println(nr);
	        			//System.out.println(service);
	            		//System.out.println(cost);
	
	            		s = new Service();
						services.add(s);
						s.AddData(nr);
						s.AddData(service);
						s.AddData(cost);
						s.AddData(curSeq);
                	}
                	
                	counter++;
                }
            }
		}
	}
	
	public void CreateSimplePDF() throws Exception
	{
		FileDialog fc2 = new FileDialog(this, "Spara", FileDialog.SAVE);
		fc2.setFile("*.pdf");
		fc2.setVisible(true);
		
		String filename = fc2.getDirectory() + fc2.getFile();
		//System.out.println("FILNAMN: " + filename);
		
		//filename = "C:/temp/simplePDF.pdf";
		
		ArrayList<String> exportData = new ArrayList<String>();
		
		exportData.add("DATUM;");
		exportData.add(textDate.getText() + ";");
		exportData.add("");
		
		exportData.add("KUND;");
		exportData.add(textCostemerName.getText() + ";");
		exportData.add("");
		
		exportData.add("TILLVERKAS;");
		exportData.add(textProduce.getText() + ";");
		exportData.add("");
		
		exportData.add("ANTAL;");
		exportData.add(textAmount.getText() + ";");
		exportData.add("");
		
		exportData.add("MO LO AFFO VINST;");
		exportData.add(textMo.getText() + ";   " + 
						textLo.getText() + ";   " + 
						textAffo.getText() + ";   " +
						textVinst.getText() + ";");
		exportData.add("");
		
		/*if (services.size() > 0)
		{
			exportData.add("METODER;");
			exportData.add(Integer.toString(services.size()) + ";");
			for (int i = 0; i < services.size(); i++)
			{
				exportData.add(services.get(i).data_.toArray()[0].toString() + ";    " +
						services.get(i).data_.toArray()[1].toString() + ";    " + 
						services.get(i).data_.toArray()[2].toString() + ";    " +
						services.get(i).data_.toArray()[3].toString() + ";");
			}
			exportData.add("");
		}*/
		
		
		if (dtmService.getRowCount() > 0)
		{
			exportData.add("SERVICE;");
			exportData.add(Integer.toString(dtmService.getRowCount()) + ";");
			/*exportData.add(dtmService.getColumnName(0) + ";   " +
					dtmService.getColumnName(1) + ";   " +
					dtmService.getColumnName(2) + ";   " +
					dtmService.getColumnName(3) + ";   " +
					dtmService.getColumnName(4) + ";   " +
					dtmService.getColumnName(5) + ";   " +
					dtmService.getColumnName(6) + ";");
			*/		
			for (int i = 0; i < dtmService.getRowCount(); i++)
			{
				exportData.add(dtmService.getValueAt(i,0).toString() + ";   " + 
						dtmService.getValueAt(i,1).toString() + ";   " + 
						dtmService.getValueAt(i,2).toString() + ";   " +
						dtmService.getValueAt(i,3).toString() + ";   " +
						dtmService.getValueAt(i,4).toString() + ";   " +
						dtmService.getValueAt(i,5).toString() + ";   " +
						SumRounder(dtmService.getValueAt(i,6).toString()) + ";   ");
			}
			
			exportData.add("");
		}
		
		if (dtmMarieial.getRowCount() > 0)
		{
			exportData.add("MATERIAL;");
			exportData.add(Integer.toString(dtmMarieial.getRowCount()) + ";");
			/*exportData.add(dtmMarieial.getColumnName(0) + ";   " +
					dtmMarieial.getColumnName(1) + ";   " +
					dtmMarieial.getColumnName(2) + ";   " +
					dtmMarieial.getColumnName(3) + ";   " +
					dtmMarieial.getColumnName(4) + ";   " +
					dtmMarieial.getColumnName(5) + ";   " +
					dtmMarieial.getColumnName(6) + ";");
				*/	
			for (int i = 0; i < dtmMarieial.getRowCount(); i++)
			{
				exportData.add(dtmMarieial.getValueAt(i,0).toString() + ";   " + 
						dtmMarieial.getValueAt(i,1).toString() + ";   " + 
						dtmMarieial.getValueAt(i,2).toString() + ";   " +
						dtmMarieial.getValueAt(i,3).toString() + ";   " +
						dtmMarieial.getValueAt(i,4).toString() + ";   " +
						dtmMarieial.getValueAt(i,5).toString() + ";   " +
						SumRounder(dtmMarieial.getValueAt(i,6).toString()) + ";   ");
			}
			exportData.add("");
		}
		
		
		if (dtmYour.getRowCount() > 0)
		{
			exportData.add("ERA KOSTNADER;");
			exportData.add(Integer.toString(dtmYour.getRowCount()) + ";");
			/*exportData.add(dtmYour.getColumnName(0) + ";   " +
					dtmYour.getColumnName(1) + ";   " +
					dtmYour.getColumnName(2) + ";   " +
					dtmYour.getColumnName(3) + ";   " +
					dtmYour.getColumnName(4) + ";   " +
					dtmYour.getColumnName(5) + ";   " +
					dtmYour.getColumnName(6) + ";");
				*/	
			for (int i = 0; i < dtmYour.getRowCount(); i++)
			{
				exportData.add(dtmYour.getValueAt(i,0).toString() + ";   " + 
						dtmYour.getValueAt(i,1).toString() + ";   " + 
						dtmYour.getValueAt(i,2).toString() + ";   " +
						dtmYour.getValueAt(i,3).toString() + ";   " +
						dtmYour.getValueAt(i,4).toString() + ";   " +
						dtmYour.getValueAt(i,5).toString() + ";   " +
						dtmYour.getValueAt(i,6).toString() + ";   " +
						SumRounder(dtmYour.getValueAt(i,7).toString()) + ";");
			}
			exportData.add("");
		}
		
		for (int i = 0; i < exportData.size(); i++)
		{
			exportData.set(i, exportData.get(i).replace(",", "."));
		}
		
		ArrayList<String> exportData2 = new ArrayList<String>();
		
		if (dtmServiceCost.getRowCount() > 0)
		{
			exportData2.add("TOTALA SERVICEKOSTNADER;");
			exportData2.add(SumRounder(textTotalServiceCost.getText()) + ";");
			/*
			for (int i = 0; i < dtmServiceCost.getRowCount(); i++)
			{
				exportData2.add(dtmServiceCost.getValueAt(i, 0) + ";");
			}
			*/
			exportData2.add("");
		}
		
		if (dtmMarieialCost.getRowCount() > 0)
		{
			exportData2.add("TOTALA MATERIALKOSTNADER;");
			exportData2.add(SumRounder(textTotalMaterialCost.getText()) + ";");
			/*for (int i = 0; i < dtmMarieialCost.getRowCount(); i++)
			{
				exportData2.add(dtmMarieialCost.getValueAt(i, 0) + ";");
			}
			*/
			exportData2.add("");
		}
		
		if (dtmYourCost.getRowCount() > 0)
		{
			exportData2.add("ERA TOTALA KOSTNADER;");
			exportData2.add(SumRounder(textYourTotalCost.getText()) + ";");
			/*
			for (int i = 0; i < dtmYourCost.getRowCount(); i++)
			{
				exportData2.add(dtmYourCost.getValueAt(i, 0) + ";");
			}
			*/
			exportData2.add("");
		}
		
		if (dtmPrepTime.getRowCount() > 0)
		{
			exportData2.add("TOTAL FÖRBEREDELSETID;");
			exportData2.add(SumRounder(textTotalPrepareTime.getText()) + ";");
			/*
			for (int i = 0; i < dtmPrepTime.getRowCount(); i++)
			{
				exportData2.add(dtmPrepTime.getValueAt(i, 0) + ";");
			}
			*/
			exportData2.add("");
		}
		
		if (dtmOperationTime.getRowCount() > 0)
		{
			exportData2.add("TOTAL OPERATIONSTID;");
			exportData2.add(SumRounder(textTotalOperationTime.getText()) + ";");
			/*
			for (int i = 0; i < dtmOperationTime.getRowCount(); i++)
			{
				exportData2.add(dtmOperationTime.getValueAt(i, 0) + ";");
			}
			*/
			exportData2.add("");
		}
		
		exportData2.add("TOTAL TID;");
		exportData2.add(SumRounder(textTotalTime.getText()) + ";");
		exportData2.add("");
		
		if (dtmOneServiceCost.getRowCount() > 0)
		{
			exportData2.add("EN SERVICE;");
			for (int i = 0; i < dtmOneServiceCost.getRowCount(); i++)
			{
				exportData2.add(SumRounder(dtmOneServiceCost.getValueAt(i, 0).toString()) + ";");
			}
			exportData2.add("");
		}
		
		if (dtmOneYourCost.getRowCount() > 0)
		{
			exportData2.add("EN ERA;");
			for (int i = 0; i < dtmOneYourCost.getRowCount(); i++)
			{
				exportData2.add(SumRounder(dtmOneYourCost.getValueAt(i, 0).toString()) + ";");
			}
			exportData2.add("");
		}
		
		exportData2.add("FRAKT;");
		exportData2.add(textShippingCost.getText() + ";");
		exportData2.add("");
		
		exportData2.add("STYCKSUMMA VID FLERA BESTÄLLDA");
		exportData2.add(textAmuntDivided.getText() + ";");
		exportData2.add("");
		
		exportData2.add("STYCKSUMMA ");
		exportData2.add(textUnitAmaunt.getText() + ";");
		exportData2.add("");
		
		exportData2.add("TOTALKOSTNAD");
		exportData2.add(textTotalAmount.getText() + ";");
		exportData2.add("");
		
		try (PDDocument doc = new PDDocument())
		{
			
			PDPage page = new PDPage();
			doc.addPage(page);
		
			PDFont font = PDType1Font.HELVETICA;
			
			File f = new File("bin/hv2.png");
			try (PDPageContentStream contents = new PDPageContentStream(doc, page))
			{
				
				PDImageXObject pdImage = PDImageXObject.createFromFile(f.getAbsolutePath(), doc);
				contents.drawImage(pdImage, 200, 750);
				int startLineX = 10;
				int startLineY = 750;
				for (int i = 0; i < exportData.size(); i++)
				{
					contents.beginText();
					contents.setFont(font, 10);
					contents.setNonStrokingColor(Color.BLACK);
					contents.newLineAtOffset(startLineX, startLineY);
					contents.showText(exportData.get(i));
					contents.endText();
					startLineY -= 12;
				}
			}
			
			PDPage page2 = new PDPage();
			doc.addPage(page2);
			
			try (PDPageContentStream contents = new PDPageContentStream(doc, page2))
			{
				PDImageXObject pdImage = PDImageXObject.createFromFile(f.getAbsolutePath(), doc);
				contents.drawImage(pdImage, 200, 750);
				int startLineX = 10;
				int startLineY = 750;
				for (int i = 0; i < exportData2.size(); i++)
				{
					contents.beginText();
					contents.setFont(font, 10);
					contents.setNonStrokingColor(Color.BLACK);
					contents.newLineAtOffset(startLineX, startLineY);
					contents.showText(exportData2.get(i));
					contents.endText();
					startLineY -= 12;
				}
			}
           
			doc.save(filename);
		}
	}
	
	//------------

	static class DecimalFormatRenderer extends DefaultTableCellRenderer
	{   
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

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
			return super.getTableCellRendererComponent(table, number, isSelected, hasFocus, row, column );
		}
	}
}