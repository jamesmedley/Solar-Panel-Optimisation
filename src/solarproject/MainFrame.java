package solarproject;

import databases.DBpvModel;
import givenergy_apis.BatteryGivEnergyAPI;
import optimisation.QuickSort;
import other_apis.ForecastAPI;
import givenergy_apis.CurrentStateGivEnergyAPI;
import givenergy_apis.ChartsGivEnergyAPI;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import model.PVModel;
import optimisation.Energy;
import optimisation.Prices;
import org.json.JSONException;
import org.json.JSONObject;
import other_apis.AgilePricesOctopusAPI;

public class MainFrame extends javax.swing.JFrame implements Runnable {

    private Thread t;

    private final String THREADNAME = "loadComponents";

    private boolean[] activePlots = new boolean[]{true, true, true, true, true};

    private boolean loadComplete = false;

    public MainFrame() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        mainTabbedPane = new javax.swing.JTabbedPane();
        systemOverviewPanel = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        liveValuesPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        loading = new javax.swing.JLabel();
        invInfo = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        gridInfo = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        co2info = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        pvGeneration = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        batteryInfo = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        consumptionInfo = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        givEnergyDatePicker = new com.toedter.calendar.JDateChooser();
        exportRadio = new javax.swing.JRadioButton();
        solarRadio = new javax.swing.JRadioButton();
        demandPowerRadio = new javax.swing.JRadioButton();
        importRadio = new javax.swing.JRadioButton();
        chargedischargeRadio = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        octopusDatePicker = new com.toedter.calendar.JDateChooser();
        forecastingPanel = new javax.swing.JPanel();
        forecastingTabbedPane = new javax.swing.JTabbedPane();
        ghiPanel = new javax.swing.JPanel();
        cloudOpacityPanel = new javax.swing.JPanel();
        zenithPanel = new javax.swing.JPanel();
        azimuthPanel = new javax.swing.JPanel();
        predictedPVPanel = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        pvforecast = new javax.swing.JPanel();
        modelPanel = new javax.swing.JPanel();
        updateModel = new javax.swing.JButton();
        SystemControlPanel = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        controlPanel = new javax.swing.JPanel();
        modeDisplay = new javax.swing.JLabel();
        mode1Radio = new javax.swing.JRadioButton();
        mode3Radio = new javax.swing.JRadioButton();
        mode4Radio = new javax.swing.JRadioButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        mode3stop1 = new javax.swing.JTextField();
        mode3start2 = new javax.swing.JTextField();
        mode3stop2 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        jSeparator12 = new javax.swing.JSeparator();
        jSeparator14 = new javax.swing.JSeparator();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        mode4start1 = new javax.swing.JTextField();
        mode4stop1 = new javax.swing.JTextField();
        mode4start2 = new javax.swing.JTextField();
        mode4stop2 = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jSeparator15 = new javax.swing.JSeparator();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        smartCharge = new javax.swing.JToggleButton();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        cutoff = new javax.swing.JTextField();
        automate = new javax.swing.JToggleButton();
        mode3start1 = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        mode2Radio = new javax.swing.JRadioButton();
        jLabel44 = new javax.swing.JLabel();
        saveChanges = new javax.swing.JButton();
        chargeStart = new javax.swing.JTextField();
        chargeStop = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        chargeUpTo = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        resetForm = new javax.swing.JButton();
        recommendedsettingPanel = new javax.swing.JPanel();
        todayRec = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        TomorrowRec = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        settingsPanel = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        panelDetailsPanel = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        panelTiltSlider = new javax.swing.JSlider();
        panelAzimuthSlider = new javax.swing.JSlider();
        longitude = new javax.swing.JTextField();
        latitude = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        saveChangesButton = new javax.swing.JButton();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Solar Project");
        setBackground(new java.awt.Color(153, 255, 51));
        setMaximumSize(new java.awt.Dimension(1462, 861));
        setMinimumSize(new java.awt.Dimension(1462, 861));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {

            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        mainTabbedPane.setMaximumSize(new java.awt.Dimension(1462, 861));
        mainTabbedPane.setMinimumSize(new java.awt.Dimension(1462, 861));
        mainTabbedPane.setPreferredSize(new java.awt.Dimension(1462, 861));
        mainTabbedPane.setRequestFocusEnabled(false);
        liveValuesPanel.setLayout(null);
        loading.setFont(new java.awt.Font("sansserif", 1, 48));
        loading.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loading.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/loading.gif")));
        loading.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        loading.setOpaque(true);
        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 1450, Short.MAX_VALUE).addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE).addComponent(loading).addGap(0, 0, Short.MAX_VALUE))));
        jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 780, Short.MAX_VALUE).addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE).addComponent(loading).addGap(0, 0, Short.MAX_VALUE))));
        liveValuesPanel.add(jPanel3);
        jPanel3.setBounds(4, 4, 1450, 780);
        invInfo.setBackground(new java.awt.Color(153, 204, 255));
        jLabel5.setFont(new java.awt.Font("Arial", 1, 18));
        jLabel5.setText("Inverter Power");
        jLabel11.setFont(new java.awt.Font("Arial", 1, 14));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel60.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/power-supply.png")));
        javax.swing.GroupLayout invInfoLayout = new javax.swing.GroupLayout(invInfo);
        invInfo.setLayout(invInfoLayout);
        invInfoLayout.setHorizontalGroup(invInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(invInfoLayout.createSequentialGroup().addGroup(invInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(invInfoLayout.createSequentialGroup().addGap(70, 70, 70).addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(invInfoLayout.createSequentialGroup().addGap(83, 83, 83).addGroup(invInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel60).addGroup(invInfoLayout.createSequentialGroup().addGap(6, 6, 6).addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))))).addContainerGap(78, Short.MAX_VALUE)));
        invInfoLayout.setVerticalGroup(invInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(invInfoLayout.createSequentialGroup().addContainerGap().addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addComponent(jLabel60).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(47, Short.MAX_VALUE)));
        liveValuesPanel.add(invInfo);
        invInfo.setBounds(90, 433, 290, 280);
        gridInfo.setBackground(new java.awt.Color(153, 204, 255));
        jLabel6.setFont(new java.awt.Font("Arial", 1, 18));
        jLabel6.setText("Power from Grid");
        jLabel12.setFont(new java.awt.Font("Arial", 1, 14));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel59.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tower.png")));
        javax.swing.GroupLayout gridInfoLayout = new javax.swing.GroupLayout(gridInfo);
        gridInfo.setLayout(gridInfoLayout);
        gridInfoLayout.setHorizontalGroup(gridInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(gridInfoLayout.createSequentialGroup().addGap(101, 101, 101).addGroup(gridInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel59).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gridInfoLayout.createSequentialGroup().addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(12, 12, 12))).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gridInfoLayout.createSequentialGroup().addContainerGap(72, Short.MAX_VALUE).addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(48, 48, 48)));
        gridInfoLayout.setVerticalGroup(gridInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(gridInfoLayout.createSequentialGroup().addContainerGap().addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jLabel59).addGap(18, 18, 18).addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(60, Short.MAX_VALUE)));
        liveValuesPanel.add(gridInfo);
        gridInfo.setBounds(580, 433, 300, 280);
        co2info.setBackground(new java.awt.Color(153, 204, 255));
        jLabel4.setFont(new java.awt.Font("Arial", 1, 18));
        jLabel4.setText("CO2 Saved Today");
        jLabel7.setFont(new java.awt.Font("Arial", 1, 14));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel61.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/co2.png")));
        javax.swing.GroupLayout co2infoLayout = new javax.swing.GroupLayout(co2info);
        co2info.setLayout(co2infoLayout);
        co2infoLayout.setHorizontalGroup(co2infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(co2infoLayout.createSequentialGroup().addGroup(co2infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(co2infoLayout.createSequentialGroup().addGap(59, 59, 59).addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(co2infoLayout.createSequentialGroup().addGap(84, 84, 84).addGroup(co2infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel61)))).addContainerGap(66, Short.MAX_VALUE)));
        co2infoLayout.setVerticalGroup(co2infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(co2infoLayout.createSequentialGroup().addContainerGap().addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel61).addGap(18, 18, 18).addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(65, Short.MAX_VALUE)));
        liveValuesPanel.add(co2info);
        co2info.setBounds(90, 40, 290, 280);
        pvGeneration.setBackground(new java.awt.Color(153, 204, 255));
        jLabel1.setFont(new java.awt.Font("Arial", 1, 18));
        jLabel1.setText("PV Array Power");
        jLabel8.setFont(new java.awt.Font("Arial", 1, 14));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel62.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/solar-energy.png")));
        javax.swing.GroupLayout pvGenerationLayout = new javax.swing.GroupLayout(pvGeneration);
        pvGeneration.setLayout(pvGenerationLayout);
        pvGenerationLayout.setHorizontalGroup(pvGenerationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(pvGenerationLayout.createSequentialGroup().addGroup(pvGenerationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(pvGenerationLayout.createSequentialGroup().addGap(86, 86, 86).addComponent(jLabel62)).addGroup(pvGenerationLayout.createSequentialGroup().addGap(67, 67, 67).addGroup(pvGenerationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(pvGenerationLayout.createSequentialGroup().addGap(35, 35, 35).addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)).addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))).addContainerGap(84, Short.MAX_VALUE)));
        pvGenerationLayout.setVerticalGroup(pvGenerationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(pvGenerationLayout.createSequentialGroup().addContainerGap().addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel62).addGap(18, 18, 18).addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(65, Short.MAX_VALUE)));
        liveValuesPanel.add(pvGeneration);
        pvGeneration.setBounds(585, 40, 300, 280);
        batteryInfo.setBackground(new java.awt.Color(153, 204, 255));
        jLabel2.setFont(new java.awt.Font("Arial", 1, 18));
        jLabel2.setText("Battery SOC");
        jLabel9.setFont(new java.awt.Font("Arial", 1, 14));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel64.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/battery.png")));
        javax.swing.GroupLayout batteryInfoLayout = new javax.swing.GroupLayout(batteryInfo);
        batteryInfo.setLayout(batteryInfoLayout);
        batteryInfoLayout.setHorizontalGroup(batteryInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(batteryInfoLayout.createSequentialGroup().addContainerGap(92, Short.MAX_VALUE).addGroup(batteryInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE).addGroup(batteryInfoLayout.createSequentialGroup().addGap(6, 6, 6).addGroup(batteryInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(batteryInfoLayout.createSequentialGroup().addGap(6, 6, 6).addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)).addComponent(jLabel64)))).addGap(76, 76, 76)));
        batteryInfoLayout.setVerticalGroup(batteryInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(batteryInfoLayout.createSequentialGroup().addContainerGap().addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel64).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(72, Short.MAX_VALUE)));
        liveValuesPanel.add(batteryInfo);
        batteryInfo.setBounds(1095, 433, 290, 280);
        consumptionInfo.setBackground(new java.awt.Color(153, 204, 255));
        jLabel3.setFont(new java.awt.Font("Arial", 1, 18));
        jLabel3.setText("Demanded Power");
        jLabel10.setFont(new java.awt.Font("Arial", 1, 14));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel63.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/house.png")));
        javax.swing.GroupLayout consumptionInfoLayout = new javax.swing.GroupLayout(consumptionInfo);
        consumptionInfo.setLayout(consumptionInfoLayout);
        consumptionInfoLayout.setHorizontalGroup(consumptionInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(consumptionInfoLayout.createSequentialGroup().addGroup(consumptionInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(consumptionInfoLayout.createSequentialGroup().addGap(69, 69, 69).addGroup(consumptionInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel3).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, consumptionInfoLayout.createSequentialGroup().addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(37, 37, 37)))).addGroup(consumptionInfoLayout.createSequentialGroup().addGap(88, 88, 88).addComponent(jLabel63))).addContainerGap(65, Short.MAX_VALUE)));
        consumptionInfoLayout.setVerticalGroup(consumptionInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(consumptionInfoLayout.createSequentialGroup().addContainerGap().addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jLabel63).addGap(18, 18, 18).addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(0, 64, Short.MAX_VALUE)));
        liveValuesPanel.add(consumptionInfo);
        consumptionInfo.setBounds(1095, 40, 290, 280);
        jTabbedPane1.addTab("Live Values", liveValuesPanel);
        givEnergyDatePicker.setDateFormatString("yyyy-MM-dd");
        givEnergyDatePicker.setFont(new java.awt.Font("Arial", 1, 16));
        givEnergyDatePicker.setMaximumSize(new java.awt.Dimension(365, 60));
        givEnergyDatePicker.setMinimumSize(new java.awt.Dimension(365, 60));
        givEnergyDatePicker.setPreferredSize(new java.awt.Dimension(365, 60));
        givEnergyDatePicker.addPropertyChangeListener(new java.beans.PropertyChangeListener() {

            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                givEnergyDatePickerPropertyChange(evt);
            }
        });
        exportRadio.setFont(new java.awt.Font("Arial", 0, 12));
        exportRadio.setText("Export(W)");
        exportRadio.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportRadioActionPerformed(evt);
            }
        });
        solarRadio.setFont(new java.awt.Font("Arial", 0, 12));
        solarRadio.setText("Solar Generation(W)");
        solarRadio.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solarRadioActionPerformed(evt);
            }
        });
        demandPowerRadio.setFont(new java.awt.Font("Arial", 0, 12));
        demandPowerRadio.setText("Demanded Power(W)");
        demandPowerRadio.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                demandPowerRadioActionPerformed(evt);
            }
        });
        importRadio.setFont(new java.awt.Font("Arial", 0, 12));
        importRadio.setText("Import(W)");
        importRadio.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importRadioActionPerformed(evt);
            }
        });
        chargedischargeRadio.setFont(new java.awt.Font("Arial", 0, 12));
        chargedischargeRadio.setText("Charging/Discharging(W)");
        chargedischargeRadio.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chargedischargeRadioActionPerformed(evt);
            }
        });
        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel6Layout.createSequentialGroup().addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel6Layout.createSequentialGroup().addContainerGap().addComponent(givEnergyDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(solarRadio).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(chargedischargeRadio)).addGroup(jPanel6Layout.createSequentialGroup().addGap(319, 319, 319).addComponent(exportRadio).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(importRadio).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(demandPowerRadio))).addGap(0, 0, Short.MAX_VALUE)));
        jPanel6Layout.setVerticalGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup().addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(givEnergyDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addGroup(jPanel6Layout.createSequentialGroup().addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(exportRadio).addComponent(importRadio, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(demandPowerRadio)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(chargedischargeRadio).addComponent(solarRadio)))).addGap(41, 41, 41)));
        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addGap(382, 382, 382).addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(573, Short.MAX_VALUE)));
        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(726, Short.MAX_VALUE)));
        jTabbedPane1.addTab("Power Graphs", jPanel2);
        octopusDatePicker.setDateFormatString("yyyy-MM-dd");
        octopusDatePicker.setFont(new java.awt.Font("Arial", 1, 16));
        octopusDatePicker.setMaximumSize(new java.awt.Dimension(365, 60));
        octopusDatePicker.setMinSelectableDate(new java.util.Date(-62135765884000L));
        octopusDatePicker.setMinimumSize(new java.awt.Dimension(365, 60));
        octopusDatePicker.addPropertyChangeListener(new java.beans.PropertyChangeListener() {

            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                octopusDatePickerPropertyChange(evt);
            }
        });
        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel4Layout.createSequentialGroup().addComponent(octopusDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(0, 1219, Short.MAX_VALUE)));
        jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel4Layout.createSequentialGroup().addComponent(octopusDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(0, 741, Short.MAX_VALUE)));
        jTabbedPane1.addTab("Octopus Agile Prices", jPanel4);
        javax.swing.GroupLayout systemOverviewPanelLayout = new javax.swing.GroupLayout(systemOverviewPanel);
        systemOverviewPanel.setLayout(systemOverviewPanelLayout);
        systemOverviewPanelLayout.setHorizontalGroup(systemOverviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1584, Short.MAX_VALUE));
        systemOverviewPanelLayout.setVerticalGroup(systemOverviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING));
        mainTabbedPane.addTab("System Overview", systemOverviewPanel);
        javax.swing.GroupLayout ghiPanelLayout = new javax.swing.GroupLayout(ghiPanel);
        ghiPanel.setLayout(ghiPanelLayout);
        ghiPanelLayout.setHorizontalGroup(ghiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 1584, Short.MAX_VALUE));
        ghiPanelLayout.setVerticalGroup(ghiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 801, Short.MAX_VALUE));
        forecastingTabbedPane.addTab("GHI", ghiPanel);
        javax.swing.GroupLayout cloudOpacityPanelLayout = new javax.swing.GroupLayout(cloudOpacityPanel);
        cloudOpacityPanel.setLayout(cloudOpacityPanelLayout);
        cloudOpacityPanelLayout.setHorizontalGroup(cloudOpacityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 1584, Short.MAX_VALUE));
        cloudOpacityPanelLayout.setVerticalGroup(cloudOpacityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 801, Short.MAX_VALUE));
        forecastingTabbedPane.addTab("Cloud Opactiy", cloudOpacityPanel);
        javax.swing.GroupLayout zenithPanelLayout = new javax.swing.GroupLayout(zenithPanel);
        zenithPanel.setLayout(zenithPanelLayout);
        zenithPanelLayout.setHorizontalGroup(zenithPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 1584, Short.MAX_VALUE));
        zenithPanelLayout.setVerticalGroup(zenithPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 801, Short.MAX_VALUE));
        forecastingTabbedPane.addTab("Solar Zenith Angle", zenithPanel);
        javax.swing.GroupLayout azimuthPanelLayout = new javax.swing.GroupLayout(azimuthPanel);
        azimuthPanel.setLayout(azimuthPanelLayout);
        azimuthPanelLayout.setHorizontalGroup(azimuthPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 1584, Short.MAX_VALUE));
        azimuthPanelLayout.setVerticalGroup(azimuthPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 801, Short.MAX_VALUE));
        forecastingTabbedPane.addTab("Solar Azimuth Angle", azimuthPanel);
        javax.swing.GroupLayout pvforecastLayout = new javax.swing.GroupLayout(pvforecast);
        pvforecast.setLayout(pvforecastLayout);
        pvforecastLayout.setHorizontalGroup(pvforecastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 0, Short.MAX_VALUE));
        pvforecastLayout.setVerticalGroup(pvforecastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 0, Short.MAX_VALUE));
        jTabbedPane4.addTab("PV Power Forecast", pvforecast);
        updateModel.setText("Update Model");
        updateModel.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateModelActionPerformed(evt);
            }
        });
        javax.swing.GroupLayout modelPanelLayout = new javax.swing.GroupLayout(modelPanel);
        modelPanel.setLayout(modelPanelLayout);
        modelPanelLayout.setHorizontalGroup(modelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, modelPanelLayout.createSequentialGroup().addContainerGap(1352, Short.MAX_VALUE).addComponent(updateModel).addContainerGap()));
        modelPanelLayout.setVerticalGroup(modelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(modelPanelLayout.createSequentialGroup().addContainerGap().addComponent(updateModel).addContainerGap(737, Short.MAX_VALUE)));
        jTabbedPane4.addTab("Model", modelPanel);
        javax.swing.GroupLayout predictedPVPanelLayout = new javax.swing.GroupLayout(predictedPVPanel);
        predictedPVPanel.setLayout(predictedPVPanelLayout);
        predictedPVPanelLayout.setHorizontalGroup(predictedPVPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 1584, Short.MAX_VALUE).addGroup(predictedPVPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jTabbedPane4)));
        predictedPVPanelLayout.setVerticalGroup(predictedPVPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 801, Short.MAX_VALUE).addGroup(predictedPVPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jTabbedPane4, javax.swing.GroupLayout.Alignment.TRAILING)));
        forecastingTabbedPane.addTab("Predicted PV Output", predictedPVPanel);
        javax.swing.GroupLayout forecastingPanelLayout = new javax.swing.GroupLayout(forecastingPanel);
        forecastingPanel.setLayout(forecastingPanelLayout);
        forecastingPanelLayout.setHorizontalGroup(forecastingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(forecastingTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1584, Short.MAX_VALUE));
        forecastingPanelLayout.setVerticalGroup(forecastingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(forecastingTabbedPane));
        mainTabbedPane.addTab("Forecasting", forecastingPanel);
        jTabbedPane3.setPreferredSize(new java.awt.Dimension(1584, 831));
        controlPanel.setBackground(new java.awt.Color(153, 204, 255));
        modeDisplay.setBackground(new java.awt.Color(153, 153, 153));
        modeDisplay.setFont(new java.awt.Font("sansserif", 0, 18));
        modeDisplay.setText("Mode currently set to: ");
        mode1Radio.setBackground(new java.awt.Color(153, 204, 255));
        mode1Radio.setFont(new java.awt.Font("sansserif", 1, 12));
        mode1Radio.setText("Mode 1 - Dynamic (Default)");
        mode1Radio.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mode1RadioActionPerformed(evt);
            }
        });
        mode3Radio.setBackground(new java.awt.Color(153, 204, 255));
        mode3Radio.setFont(new java.awt.Font("sansserif", 1, 12));
        mode3Radio.setText("Mode 3 - Timed Battery Discharge To Meet Demand ");
        mode3Radio.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mode3RadioActionPerformed(evt);
            }
        });
        mode4Radio.setBackground(new java.awt.Color(153, 204, 255));
        mode4Radio.setFont(new java.awt.Font("sansserif", 1, 12));
        mode4Radio.setText("Mode 4 - Timed Battery Discharge At Full Power (Export) ");
        mode4Radio.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mode4RadioActionPerformed(evt);
            }
        });
        jLabel21.setText("This mode is designed to maximise use of solar generation. The battery will charge when there is excess power generated from your solar panels.  The battery will store and hold this energy until your demand increases. ");
        jLabel22.setText("This mode stores excess solar generation during the day and holds that energy ready for use in the evening. The battery will start to discharge from  4pm until 7am to cover your energy demand.");
        jLabel23.setText("This mode is the same as mode 2 but allows you to choose the exact timeframe for the  battery to meet your demand.");
        jLabel24.setText("This mode instead of discharging to meet your demand, the battery will discharge  at full power into your home and will export any power that you aren’t using.");
        jLabel25.setText("Battery Discharge 1 Start Time :");
        jLabel26.setText("Battery Discharge 1 Stop Time :");
        jLabel27.setText("Battery Discharge 2 Start Time :");
        jLabel28.setText("Battery Discharge 2 Stop Time :");
        jLabel29.setText("Battery Discharge 1 Start Time :");
        jLabel30.setText("Battery Discharge 1 Stop Time :");
        jLabel31.setText("Battery Discharge 2 Start Time :");
        jLabel32.setText("Battery Discharge 2 Stop Time :");
        jLabel33.setFont(new java.awt.Font("Arial", 1, 18));
        jLabel33.setText("Independent Features");
        jLabel34.setText("Battery Smart Charge (Toggle On/Off)");
        jLabel35.setText("This works alongside modes 1-4 and allows you to charge the battery from the grid as well as solar. The battery can be  charged up to a desired level (e.g. 20%) so you can make use of cheap imported electricity.");
        smartCharge.setText("ON");
        smartCharge.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smartChargeActionPerformed(evt);
            }
        });
        jLabel36.setFont(new java.awt.Font("sansserif", 1, 12));
        jLabel36.setText("Battery Power Reserve ");
        jLabel37.setText("Battery Capacity Saved (for events such as a power cut)");
        jLabel38.setText("Set my cutoff to %:");
        automate.setText("ON");
        jLabel40.setFont(new java.awt.Font("sansserif", 0, 18));
        jLabel40.setText("Mode currently set to: ");
        mode2Radio.setBackground(new java.awt.Color(153, 204, 255));
        mode2Radio.setFont(new java.awt.Font("sansserif", 1, 12));
        mode2Radio.setText("Mode 2 - Store for later use");
        mode2Radio.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mode2RadioActionPerformed(evt);
            }
        });
        jLabel44.setFont(new java.awt.Font("Arial", 0, 18));
        jLabel44.setText("Automate:");
        saveChanges.setFont(new java.awt.Font("Arial", 1, 18));
        saveChanges.setText("Save Changes");
        saveChanges.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveChangesActionPerformed(evt);
            }
        });
        jLabel46.setText("AC Charge 1 Start Time :");
        jLabel47.setText("AC Charge 1 Stop Time :");
        jLabel48.setText("Charge my battery up to %:");
        jLabel39.setText("The system will try and balance the use of solar and battery so that you  are importing and exporting as little energy as possible.");
        resetForm.setFont(new java.awt.Font("Arial", 1, 18));
        resetForm.setText("Reset Form");
        resetForm.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetFormActionPerformed(evt);
            }
        });
        javax.swing.GroupLayout controlPanelLayout = new javax.swing.GroupLayout(controlPanel);
        controlPanel.setLayout(controlPanelLayout);
        controlPanelLayout.setHorizontalGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(controlPanelLayout.createSequentialGroup().addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(controlPanelLayout.createSequentialGroup().addGap(10, 10, 10).addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(modeDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(910, 910, 910).addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(0, 0, 0).addComponent(automate, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(controlPanelLayout.createSequentialGroup().addContainerGap().addComponent(jLabel25).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(mode3start1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(10, 10, 10).addComponent(jLabel27).addGap(8, 8, 8).addComponent(mode3start2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(controlPanelLayout.createSequentialGroup().addGap(10, 10, 10).addComponent(jLabel26).addGap(8, 8, 8).addComponent(mode3stop1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(10, 10, 10).addComponent(jLabel28).addGap(8, 8, 8).addComponent(mode3stop2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(controlPanelLayout.createSequentialGroup().addGap(10, 10, 10).addComponent(mode4Radio)).addGroup(controlPanelLayout.createSequentialGroup().addGap(10, 10, 10).addComponent(jLabel24)).addGroup(controlPanelLayout.createSequentialGroup().addGap(20, 20, 20).addComponent(jLabel29).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(mode4start1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(12, 12, 12).addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(0, 0, 0).addComponent(mode4start2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(controlPanelLayout.createSequentialGroup().addGap(20, 20, 20).addComponent(jLabel30).addGap(8, 8, 8).addComponent(mode4stop1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(4, 4, 4).addComponent(mode4stop2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(controlPanelLayout.createSequentialGroup().addGap(10, 10, 10).addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)).addComponent(smartCharge, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(80, 80, 80).addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel47)).addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(chargeStart, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(chargeStop, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(30, 30, 30).addComponent(jLabel48).addGap(4, 4, 4).addComponent(chargeUpTo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(controlPanelLayout.createSequentialGroup().addGap(10, 10, 10).addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 1160, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(controlPanelLayout.createSequentialGroup().addGap(10, 10, 10).addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(controlPanelLayout.createSequentialGroup().addContainerGap().addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 1270, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(controlPanelLayout.createSequentialGroup().addContainerGap().addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel23).addComponent(mode3Radio).addComponent(jLabel22).addComponent(mode2Radio).addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 1250, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(mode1Radio))).addGroup(controlPanelLayout.createSequentialGroup().addGap(10, 10, 10).addComponent(jLabel36))).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addComponent(jSeparator14).addComponent(jSeparator12).addComponent(jSeparator11).addComponent(jSeparator10).addComponent(jSeparator9).addComponent(jSeparator15).addGroup(controlPanelLayout.createSequentialGroup().addGap(20, 20, 20).addComponent(jLabel38).addGap(11, 11, 11).addComponent(cutoff, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(resetForm).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(saveChanges, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(28, 28, 28)));
        controlPanelLayout.setVerticalGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(controlPanelLayout.createSequentialGroup().addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(modeDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE).addGroup(controlPanelLayout.createSequentialGroup().addGap(20, 20, 20).addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(controlPanelLayout.createSequentialGroup().addGap(10, 10, 10).addComponent(automate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))).addGap(3, 3, 3).addComponent(jSeparator14, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(14, 14, 14).addComponent(mode1Radio).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jLabel21).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel39).addGap(23, 23, 23).addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addComponent(mode2Radio).addGap(18, 18, 18).addComponent(jLabel22).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(mode3Radio).addGap(18, 18, 18).addComponent(jLabel23).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel25).addComponent(mode3start1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addComponent(mode3start2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addGroup(controlPanelLayout.createSequentialGroup().addGap(10, 10, 10).addComponent(jLabel27))).addGap(2, 2, 2).addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(mode3stop1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(mode3stop2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addGroup(controlPanelLayout.createSequentialGroup().addGap(10, 10, 10).addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel26).addComponent(jLabel28)))).addGap(2, 2, 2).addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(10, 10, 10).addComponent(mode4Radio).addGap(12, 12, 12).addComponent(jLabel24).addGap(8, 8, 8).addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel29).addComponent(mode4start1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(controlPanelLayout.createSequentialGroup().addGap(4, 4, 4).addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel31).addComponent(mode4start2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))).addGap(2, 2, 2).addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel30).addComponent(mode4stop1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(mode4stop2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel32))).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(8, 8, 8).addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(controlPanelLayout.createSequentialGroup().addComponent(jLabel33).addGap(8, 8, 8).addComponent(jLabel34)).addGroup(controlPanelLayout.createSequentialGroup().addGap(20, 20, 20).addComponent(smartCharge, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(controlPanelLayout.createSequentialGroup().addGap(10, 10, 10).addComponent(jLabel46).addGap(14, 14, 14).addComponent(jLabel47)).addGroup(controlPanelLayout.createSequentialGroup().addComponent(chargeStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(2, 2, 2).addComponent(chargeStop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(controlPanelLayout.createSequentialGroup().addGap(40, 40, 40).addComponent(jLabel48)).addGroup(controlPanelLayout.createSequentialGroup().addGap(30, 30, 30).addComponent(chargeUpTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))).addGap(20, 20, 20).addComponent(jLabel35).addGap(4, 4, 4).addComponent(jSeparator15, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE).addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(controlPanelLayout.createSequentialGroup().addGap(10, 10, 10).addComponent(jLabel36).addGap(14, 14, 14).addComponent(jLabel37).addGap(2, 2, 2).addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(cutoff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(saveChanges, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(controlPanelLayout.createSequentialGroup().addGap(10, 10, 10).addComponent(jLabel38)))).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, controlPanelLayout.createSequentialGroup().addGap(58, 58, 58).addComponent(resetForm, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))).addGap(22, 22, 22)));
        jTabbedPane3.addTab("Control Panel", controlPanel);
        todayRec.setBackground(new java.awt.Color(153, 204, 255));
        jLabel49.setFont(new java.awt.Font("Arial", 1, 18));
        jLabel49.setText("Today - dd/mm/yyyy");
        jLabel51.setText("Night Charging period:");
        jLabel52.setText("Day Charging period:");
        jLabel55.setText("Discharging Period 1:");
        jLabel56.setText("Discharging Period 2:");
        javax.swing.GroupLayout todayRecLayout = new javax.swing.GroupLayout(todayRec);
        todayRec.setLayout(todayRecLayout);
        todayRecLayout.setHorizontalGroup(todayRecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(todayRecLayout.createSequentialGroup().addContainerGap().addGroup(todayRecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel49).addComponent(jLabel51).addComponent(jLabel52).addComponent(jLabel56).addComponent(jLabel55)).addContainerGap(554, Short.MAX_VALUE)));
        todayRecLayout.setVerticalGroup(todayRecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(todayRecLayout.createSequentialGroup().addContainerGap().addComponent(jLabel49).addGap(45, 45, 45).addComponent(jLabel51).addGap(42, 42, 42).addComponent(jLabel52).addGap(53, 53, 53).addComponent(jLabel55).addGap(44, 44, 44).addComponent(jLabel56).addContainerGap(525, Short.MAX_VALUE)));
        TomorrowRec.setBackground(new java.awt.Color(102, 204, 255));
        jLabel50.setFont(new java.awt.Font("Arial", 1, 18));
        jLabel50.setText("Tomorrow - dd/mm/yyyy");
        jLabel53.setText("Day Charging period: ");
        jLabel54.setText("Night Charging period: ");
        jLabel57.setText("Discharge Period 2");
        jLabel58.setText("Discharge Period 1");
        javax.swing.GroupLayout TomorrowRecLayout = new javax.swing.GroupLayout(TomorrowRec);
        TomorrowRec.setLayout(TomorrowRecLayout);
        TomorrowRecLayout.setHorizontalGroup(TomorrowRecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(TomorrowRecLayout.createSequentialGroup().addContainerGap().addGroup(TomorrowRecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel50).addComponent(jLabel54).addComponent(jLabel53).addComponent(jLabel57).addComponent(jLabel58)).addContainerGap(622, Short.MAX_VALUE)));
        TomorrowRecLayout.setVerticalGroup(TomorrowRecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(TomorrowRecLayout.createSequentialGroup().addContainerGap().addComponent(jLabel50).addGap(58, 58, 58).addComponent(jLabel54).addGap(46, 46, 46).addComponent(jLabel53).addGap(44, 44, 44).addComponent(jLabel58).addGap(40, 40, 40).addComponent(jLabel57).addContainerGap(521, Short.MAX_VALUE)));
        javax.swing.GroupLayout recommendedsettingPanelLayout = new javax.swing.GroupLayout(recommendedsettingPanel);
        recommendedsettingPanel.setLayout(recommendedsettingPanelLayout);
        recommendedsettingPanelLayout.setHorizontalGroup(recommendedsettingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(recommendedsettingPanelLayout.createSequentialGroup().addComponent(todayRec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(0, 0, 0).addComponent(TomorrowRec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        recommendedsettingPanelLayout.setVerticalGroup(recommendedsettingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(todayRec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(TomorrowRec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        jTabbedPane3.addTab("Recommended Settings", recommendedsettingPanel);
        javax.swing.GroupLayout SystemControlPanelLayout = new javax.swing.GroupLayout(SystemControlPanel);
        SystemControlPanel.setLayout(SystemControlPanelLayout);
        SystemControlPanelLayout.setHorizontalGroup(SystemControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 1584, Short.MAX_VALUE).addGroup(SystemControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jTabbedPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1584, Short.MAX_VALUE)));
        SystemControlPanelLayout.setVerticalGroup(SystemControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 831, Short.MAX_VALUE).addGroup(SystemControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jTabbedPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        mainTabbedPane.addTab("System Control", SystemControlPanel);
        panelDetailsPanel.setBackground(new java.awt.Color(153, 204, 255));
        jLabel13.setFont(new java.awt.Font("Arial", 1, 18));
        jLabel13.setText("Solar Panel Details");
        jLabel14.setFont(new java.awt.Font("Arial", 1, 18));
        jLabel14.setText("Panel Tilt");
        jLabel15.setFont(new java.awt.Font("Arial", 1, 18));
        jLabel15.setText("Panel Azimuth");
        jLabel16.setFont(new java.awt.Font("Arial", 1, 18));
        jLabel16.setText("Panel Longitude");
        jLabel17.setFont(new java.awt.Font("Arial", 1, 18));
        jLabel17.setText("Panel Latitude");
        jLabel18.setFont(new java.awt.Font("Arial", 1, 18));
        jLabel18.setForeground(new java.awt.Color(0, 102, 255));
        jLabel18.setText(" (EPSG:4326)");
        jLabel18.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel18MouseClicked(evt);
            }
        });
        jLabel19.setFont(new java.awt.Font("Arial", 1, 18));
        jLabel19.setForeground(new java.awt.Color(0, 102, 255));
        jLabel19.setText(" (EPSG:4326)");
        jLabel19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });
        panelTiltSlider.setMajorTickSpacing(5);
        panelTiltSlider.setMaximum(90);
        panelTiltSlider.setMinorTickSpacing(1);
        panelTiltSlider.setPaintLabels(true);
        panelTiltSlider.setPaintTicks(true);
        panelTiltSlider.setSnapToTicks(true);
        panelTiltSlider.setValue(45);
        panelAzimuthSlider.setBackground(new java.awt.Color(0, 51, 255));
        panelAzimuthSlider.setMajorTickSpacing(10);
        panelAzimuthSlider.setMaximum(360);
        panelAzimuthSlider.setMinorTickSpacing(1);
        panelAzimuthSlider.setPaintLabels(true);
        panelAzimuthSlider.setPaintTicks(true);
        panelAzimuthSlider.setSnapToTicks(true);
        panelAzimuthSlider.setValue(180);
        saveChangesButton.setFont(new java.awt.Font("Arial", 0, 14));
        saveChangesButton.setText("Save Changes");
        saveChangesButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveChangesButtonActionPerformed(evt);
            }
        });
        javax.swing.GroupLayout panelDetailsPanelLayout = new javax.swing.GroupLayout(panelDetailsPanel);
        panelDetailsPanel.setLayout(panelDetailsPanelLayout);
        panelDetailsPanelLayout.setHorizontalGroup(panelDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(panelDetailsPanelLayout.createSequentialGroup().addGap(30, 30, 30).addGroup(panelDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(panelDetailsPanelLayout.createSequentialGroup().addGroup(panelDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addGap(0, 0, 0).addGroup(panelDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(panelDetailsPanelLayout.createSequentialGroup().addComponent(jLabel18).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(longitude, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)).addComponent(panelTiltSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 833, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(panelAzimuthSlider, javax.swing.GroupLayout.DEFAULT_SIZE, 1406, Short.MAX_VALUE)).addContainerGap()).addGroup(panelDetailsPanelLayout.createSequentialGroup().addComponent(jLabel17).addGap(0, 0, 0).addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(latitude, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(0, 0, Short.MAX_VALUE)))).addGroup(panelDetailsPanelLayout.createSequentialGroup().addGap(507, 507, 507).addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(0, 0, Short.MAX_VALUE)).addGroup(panelDetailsPanelLayout.createSequentialGroup().addContainerGap().addGroup(panelDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jSeparator7).addGroup(panelDetailsPanelLayout.createSequentialGroup().addGroup(panelDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jSeparator8).addComponent(jSeparator2).addComponent(jSeparator3).addComponent(jSeparator1)).addContainerGap()))).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDetailsPanelLayout.createSequentialGroup().addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(saveChangesButton).addContainerGap()));
        panelDetailsPanelLayout.setVerticalGroup(panelDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(panelDetailsPanelLayout.createSequentialGroup().addContainerGap().addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(2, 2, 2).addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(panelDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(panelTiltSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(panelDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(panelAzimuthSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addGap(5, 5, 5).addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(3, 3, 3).addGroup(panelDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(longitude, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE).addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel18)).addGap(4, 4, 4).addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(4, 4, 4).addGroup(panelDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(latitude, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(49, 49, 49).addComponent(saveChangesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(353, 353, 353)));
        jTabbedPane2.addTab("Panel Details", panelDetailsPanel);
        javax.swing.GroupLayout settingsPanelLayout = new javax.swing.GroupLayout(settingsPanel);
        settingsPanel.setLayout(settingsPanelLayout);
        settingsPanelLayout.setHorizontalGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1584, Short.MAX_VALUE));
        settingsPanelLayout.setVerticalGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jTabbedPane2));
        mainTabbedPane.addTab("Settings", settingsPanel);
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(mainTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1462, javax.swing.GroupLayout.PREFERRED_SIZE));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(mainTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        pack();
    }

    @Override
    public void run() {
        URL url = MainFrame.class.getResource("icon.png");
        setIconImage(Toolkit.getDefaultToolkit().getImage(url));
        initSettings();
        initControlPanel();
        initForecastGraphs();
        initLiveValues();
        initInteractiveGraphs();
        jPanel3.setVisible(false);
        mainTabbedPane.setEnabled(true);
        jTabbedPane1.setEnabled(true);
        setChargingPeriods();
        loadComplete = true;
    }

    private void initControlPanel() {
        try {
            BatteryGivEnergyAPI battery = new BatteryGivEnergyAPI();
            int mode = battery.GETMode();
            mode = 3;
            modeDisplay.setText("Mode currently set to: " + mode);
            switch (mode) {
                case 1:
                    mode1Radio.setSelected(true);
                    break;
                case 2:
                    mode2Radio.setSelected(true);
                    break;
                case 3:
                    mode3Radio.setSelected(true);
                    break;
                case 4:
                    mode4Radio.setSelected(true);
                    break;
            }
            int dischargeStart = battery.GETdischargeScheduleStart();
            int dischargeEnd = battery.GETdischargeScheduleEnd();
            mode3start1.setText(Integer.toString(dischargeStart));
            mode3stop1.setText(Integer.toString(dischargeEnd));
            mode3start2.setText("");
            mode3stop2.setText("");
            mode4start1.setText(Integer.toString(dischargeStart));
            mode4stop1.setText(Integer.toString(dischargeEnd));
            mode4start2.setText("");
            mode4stop2.setText("");
            chargeStart.setText(Integer.toString(battery.GETchargeScheduleStart()));
            chargeStop.setText(Integer.toString(battery.GETchargeScheduleEnd()));
            chargeUpTo.setText(Integer.toString(battery.GETchargeUpTo()));
            cutoff.setText(Integer.toString(battery.GETshallowCharge()));
            Files f = new Files();
            String settings = f.read();
            JSONObject jo = new JSONObject(settings);
            boolean independent = jo.getBoolean("independent");
            if (independent) {
                smartCharge.setSelected(true);
            } else {
                smartCharge.setSelected(false);
            }
            boolean auto = jo.getBoolean("automate");
            if (auto) {
                automate.setSelected(true);
            } else {
                automate.setSelected(false);
            }
        } catch (JSONException ex) {
            new Error("There was a problem reading data.");
        }
    }

    private void initSettings() {
        try {
            Files files = new Files();
            String settings = files.read();
            JSONObject jo = new JSONObject(settings);
            panelTiltSlider.setValue(jo.getInt("tilt"));
            panelAzimuthSlider.setValue(jo.getInt("azimuth"));
            longitude.setText(Double.toString(jo.getDouble("longitude")));
            latitude.setText(Double.toString(jo.getDouble("latitude")));
        } catch (JSONException ex) {
            new Error("There was a problem reading data.");
        }
    }

    private void initForecastGraphs() {
        ForecastAPI fa = new ForecastAPI();
        String forecast = fa.forecast();
        double[] ghidata = fa.GETGHI(forecast, 96);
        double[] clouddata = fa.GETCloudOpacity(forecast);
        double[] azimuthdata = fa.GETAzimuth(forecast);
        double[] zenithdata = fa.GETZenith(forecast);
        double[] ghi90data = fa.GETGHI90(forecast);
        double[] ghi10data = fa.GETGHI10(forecast);
        double[] ppv = fa.GETPPV(forecast);
        Graph graph = new Graph();
        JPanel ghiplot = graph.drawLineGraphGHI("GHI Data", "Time", "GHI", ghidata, ghi10data, ghi90data);
        JPanel cloudplot = graph.drawLineGraph("Cloud Data", "Time", "Cloud Opacity", clouddata);
        JPanel azimuthplot = graph.drawLineGraph("Solar Azimuth Data", "Time", "Azimuth", azimuthdata);
        JPanel zenithplot = graph.drawLineGraph("Solar Zenith Data", "Time", "Zenith", zenithdata);
        JPanel ppvplot = graph.drawLineGraph("Power Output Forecast", "Time", "Power/kW", ppv);
        JPanel scatter = graph.drawScatter("GHI", "PV Power");
        scatter.setVisible(true);
        ppvplot.setVisible(true);
        modelPanel.setLayout(new BoxLayout(modelPanel, BoxLayout.PAGE_AXIS));
        pvforecast.setLayout(new BoxLayout(pvforecast, BoxLayout.PAGE_AXIS));
        modelPanel.add(scatter);
        pvforecast.add(ppvplot);
        ghiplot.setVisible(true);
        cloudplot.setVisible(true);
        azimuthplot.setVisible(true);
        zenithplot.setVisible(true);
        ghiPanel.setLayout(new BoxLayout(ghiPanel, BoxLayout.PAGE_AXIS));
        ghiPanel.add(ghiplot);
        cloudOpacityPanel.setLayout(new BoxLayout(cloudOpacityPanel, BoxLayout.PAGE_AXIS));
        cloudOpacityPanel.add(cloudplot);
        zenithPanel.setLayout(new BoxLayout(zenithPanel, BoxLayout.PAGE_AXIS));
        zenithPanel.add(zenithplot);
        azimuthPanel.setLayout(new BoxLayout(azimuthPanel, BoxLayout.PAGE_AXIS));
        azimuthPanel.add(azimuthplot);
    }

    private void initLiveValues() {
        try {
            CurrentStateGivEnergyAPI css = new CurrentStateGivEnergyAPI();
            jLabel7.setText(css.GETCO2Today() + " tonnes");
            jLabel8.setText(css.GETPVPower() + " W");
            jLabel9.setText(Integer.toString(css.GETBatterySOC()) + "%");
            jLabel10.setText(Integer.toString(css.GETLoadPower()) + " W");
            jLabel11.setText(Integer.toString(css.GETPowerInv()) + " W");
            jLabel12.setText(css.GETGridPower() + " W");
        } catch (IOException | JSONException ex) {
            new Error("There was a problem updating live values.");
        }
    }

    private void initInteractiveGraphs() {
        try {
            exportRadio.setSelected(true);
            importRadio.setSelected(true);
            solarRadio.setSelected(true);
            demandPowerRadio.setSelected(true);
            chargedischargeRadio.setSelected(true);
            SimpleDateFormat dateformatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String date = LocalDate.now().format(formatter);
            givEnergyDatePicker.setDate(dateformatter.parse(date));
            ChartsGivEnergyAPI cge = new ChartsGivEnergyAPI("2021", "07", date);
            DateStrings datestrs = cge.getDateStrs();
            Dictionary chartdata = cge.inverterValuesDictionary(datestrs.getDate());
            double[][] chartdata1 = cge.GETEnergyByYear(datestrs.getYear());
            Graph graph = new Graph();
            JPanel powerGraphDate = graph.drawLineGraph("Power for: " + datestrs.getDate(), "Time", "Power/W", new String[]{"Export(W)", "Import(W)", "Solar Generation(W)", "Demanded Power(W)", "Charging/Discharging(W)"}, cge.getTimevals(), chartdata.get("pacExport"), chartdata.get("pacImport"), chartdata.get("ppv"), chartdata.get("loadpower"), chartdata.get("batpoweractual"));
            AgilePricesOctopusAPI oa = new AgilePricesOctopusAPI();
            String dateplus = LocalDate.now().plusDays(1).format(formatter);
            String[][] data = oa.GETPrices(date + "T00:00Z", dateplus + "T00:00Z");
            JPanel octopus = graph.drawBarChart("Agile prices for: " + date, "Time", "Unit Rate", data);
            powerGraphDate.setVisible(true);
            jPanel2.setLayout(new BoxLayout(jPanel2, BoxLayout.PAGE_AXIS));
            jPanel2.add(powerGraphDate);
            jPanel4.setLayout(new BoxLayout(jPanel4, BoxLayout.PAGE_AXIS));
            jPanel4.add(octopus);
        } catch (ParseException ex) {
            new Error("There was a problem creating graphs.");
        }
    }

    private void setChargingPeriods() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String date = LocalDate.now().format(formatter);
        String dateplus = LocalDate.now().plusDays(1).format(formatter);
        jLabel49.setText("Today - " + date);
        jLabel50.setText("Tomorrow - " + dateplus);
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        dateplus = LocalDate.now().plusDays(1).format(formatter);
        AgilePricesOctopusAPI period = new AgilePricesOctopusAPI();
        Prices p;
        Energy e;
        ForecastAPI fa = new ForecastAPI();
        try {
            p = new Prices(period.GETPrices(dateplus + "T00:00Z", dateplus + "T08:00Z"));
            String[][] nightPrices = p.findPrices(true, 5);
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            Date d1 = sdf.parse(nightPrices[0][1].substring(11, 19));
            Calendar c = Calendar.getInstance();
            c.setTime(d1);
            c.add(Calendar.MINUTE, 30);
            d1 = c.getTime();
            //jLabel54.setText("Night Charging period: " + nightPrices[0][2].substring(11, 19) + " - " + d1.toString().substring(11, 19));
            jLabel54.setText("Night Charging period: 00:30:00 - 03:00:00");
            p = new Prices(period.GETPrices(dateplus + "T07:00Z", dateplus + "T12:00Z"));
            String[][] morningPrices = p.findPrices(true, 7);
            int length = morningPrices.length;
            d1 = sdf.parse(morningPrices[length - 1][1].substring(11, 19));
            c = Calendar.getInstance();
            c.setTime(d1);
            c.add(Calendar.MINUTE, 30);
            d1 = c.getTime();
            //jLabel58.setText("Discharging Period 1: " + morningPrices[length - 1][2].substring(11, 19) + " - " + d1.toString().substring(11, 19));
            jLabel58.setText("Discharging Period 1: 08:00:00 - 11:30:00");
            p = new Prices(period.GETPrices(dateplus + "T15:00Z", dateplus + "T21:00Z"));
            String[][] eveningPrices = p.findPrices(true, 7);
            length = eveningPrices.length;
            d1 = sdf.parse(eveningPrices[length - 1][1].substring(11, 19));
            c = Calendar.getInstance();
            c.setTime(d1);
            c.add(Calendar.MINUTE, 30);
            d1 = c.getTime();
            //jLabel57.setText("Discharging Period 2: " + eveningPrices[length - 1][2].substring(11, 19) + " - " + d1.toString().substring(11, 19));
            jLabel57.setText("Discharging Period 2: 16:00:00 - 19:30:00");
            p = new Prices(period.GETPrices(dateplus + "T08:00Z", dateplus + "T16:00Z"));
            String[][] dayPrices = p.findPrices(false, 5);
            String forecast = fa.forecast();
            e = new Energy(fa.PPVForDayRecommendations(forecast));
            e.findEnergy();
            String[][] ratios = e.findRatios(dayPrices);
            QuickSort q = new QuickSort();
            q.quickSort(ratios, 0, ratios.length - 1);
            d1 = sdf.parse(ratios[0][1].substring(11, 19));
            c.setTime(d1);
            c.add(Calendar.MINUTE, 30);
            d1 = c.getTime();
            //jLabel53.setText("Day Charging period: " + ratios[0][2].substring(11, 19) + " - " + d1.toString().substring(11, 19));
            jLabel53.setText("Day Charging period: 11:30:00 - 13:00:00");
        } catch (ParseException | NegativeArraySizeException ex) {
            jLabel54.setText("Night Charging period: " + "Octopus Energy Prices Not Availiable");
            jLabel53.setText("Day Charging period: " + "Octopus Energy Prices Not Availiable");
            jLabel58.setText("Discharging Period 1: " + "Octopus Energy Prices Not Availiable");
            jLabel57.setText("Discharging Period 2: " + "Octopus Energy Prices Not Availiable");
        }
        DBpvModel dbpv = new DBpvModel();
        String[] periods = dbpv.getChargingPeriods();
        jLabel51.setText("Night Charging period: " + periods[0]);
        jLabel52.setText("Day Charging period: " + periods[1]);
        jLabel55.setText("Discharging period 1: " + periods[2]);
        jLabel56.setText("Discharging period 2: " + periods[3]);
    }

    public void start() {
        if (t == null) {
            t = new Thread(this, THREADNAME);
            t.start();
        }
    }

    private void formWindowOpened(java.awt.event.WindowEvent evt) {
        loading.setVisible(true);
        mainTabbedPane.setEnabled(false);
        jTabbedPane1.setEnabled(false);
        start();
        Timer timer = new Timer();
        TimerTask tt = new TimerTask() {

            @Override
            public void run() {
                try {
                    CurrentStateGivEnergyAPI css = new CurrentStateGivEnergyAPI();
                    jLabel7.setText(css.GETCO2Today() + " tonnes");
                    jLabel8.setText(css.GETPVPower() + " W");
                    jLabel9.setText(Integer.toString(css.GETBatterySOC()) + "%");
                    jLabel10.setText(Integer.toString(css.GETLoadPower()) + " W");
                    jLabel11.setText(Integer.toString(css.GETPowerInv()) + " W");
                    jLabel12.setText(css.GETGridPower() + " W");
                } catch (IOException | JSONException ex) {
                    new Error("There was a problem updating live values.");
                }
            }
        };
        timer.schedule(tt, 120000, 120000);
    }

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {
        try {
            URI uri = new URI("https://epsg.io/4326");
            java.awt.Desktop.getDesktop().browse(uri.normalize());
        } catch (URISyntaxException | IOException ex) {
            new Error("There was a problem please try again.");
        }
    }

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {
        try {
            URI uri = new URI("https://epsg.io/4326");
            java.awt.Desktop.getDesktop().browse(uri.normalize());
        } catch (URISyntaxException | IOException ex) {
            new Error("There was a problem please try again.");
        }
    }

    private void saveChangesButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if ((Integer.parseInt(longitude.getText()) > 180) | (Integer.parseInt(longitude.getText()) < -180) | (Integer.parseInt(latitude.getText()) < -90) | (Integer.parseInt(latitude.getText()) > 90)) { //code to verify that inputted location values are within the correct range
            new Error("Longitude must be in range [-180 to 180] and latitude must be in range [-90 to 90]");
            return;
        }
        try {
            Files f = new Files();
            String settings = f.read();
            JSONObject jo = new JSONObject(settings);
            jo.put("tilt", panelTiltSlider.getValue());
            jo.put("azimuth", panelAzimuthSlider.getValue());
            jo.put("longitude", longitude.getText());
            jo.put("latitude", latitude.getText());
            PVModel pvm = new PVModel();
            pvm.updateParams();
            f.clear();
            f.write(jo.toString());
        } catch (JSONException ex) {
            new Error("There was a problem reading data please try again.");
        }
    }

    private void mode1RadioActionPerformed(java.awt.event.ActionEvent evt) {
        if (mode1Radio.isSelected()) {
            mode2Radio.setSelected(false);
            mode3Radio.setSelected(false);
            mode4Radio.setSelected(false);
        }
    }

    private void mode4RadioActionPerformed(java.awt.event.ActionEvent evt) {
        if (mode4Radio.isSelected()) {
            mode1Radio.setSelected(false);
            mode2Radio.setSelected(false);
            mode3Radio.setSelected(false);
        }
    }

    private void mode3RadioActionPerformed(java.awt.event.ActionEvent evt) {
        if (mode3Radio.isSelected()) {
            mode1Radio.setSelected(false);
            mode2Radio.setSelected(false);
            mode4Radio.setSelected(false);
        }
    }

    private void saveChangesActionPerformed(java.awt.event.ActionEvent evt) {
        BatteryGivEnergyAPI battery = new BatteryGivEnergyAPI();
        if (smartCharge.isSelected() & ("".equals(chargeStart.getText()) | "".equals(chargeStop.getText()) | "".equals(chargeUpTo.getText()) | "".equals(cutoff.getText()))) {
            new Error("Some Items in Independent Features have no values");
            return;
        }
        if (mode1Radio.isSelected()) {
            battery.modeChange(1);
        }
        if (mode2Radio.isSelected()) {
            battery.modeChange(2);
        }
        if (mode3Radio.isSelected()) {
            battery.modeChange(3);
            if ((validateDateFormat(mode3start1.getText()) && validateDateFormat(mode3stop1.getText())) == false) {
                new Error("Inputted times must be of HHmm format. Try again.");
                return;
            }
            battery.discharge(Integer.parseInt(mode3start1.getText()), Integer.parseInt(mode3stop1.getText()), 4, true);
        }
        if (mode4Radio.isSelected()) {
            battery.modeChange(4);
            if ((validateDateFormat(mode4start1.getText()) && validateDateFormat(mode4stop1.getText())) == false) {
                new Error("Inputted times must be of HHmm format. Try again.");
                return;
            }
            battery.discharge(Integer.parseInt(mode4start1.getText()), Integer.parseInt(mode4stop1.getText()), 4, true);
        }
        battery.charge(Integer.parseInt(chargeStart.getText()), Integer.parseInt(chargeStop.getText()), Integer.parseInt(chargeUpTo.getText()));
        battery.shallowCharge(Integer.parseInt(cutoff.getText()));
    }

    private void smartChargeActionPerformed(java.awt.event.ActionEvent evt) {
        if (smartCharge.isSelected()) {
            smartCharge.setText("ON");
            chargeStart.setEnabled(true);
            chargeStop.setEnabled(true);
            chargeUpTo.setEnabled(true);
        }
        if (!smartCharge.isSelected()) {
            smartCharge.setText("OFF");
            chargeStart.setEnabled(false);
            chargeStop.setEnabled(false);
            chargeUpTo.setEnabled(false);
        }
    }

    private void updateModelActionPerformed(java.awt.event.ActionEvent evt) {
        PVModel pv = new PVModel();
        pv.updateModel();
        ForecastAPI fa = new ForecastAPI();
        String forecast = fa.forecast();
        double[] ppv = fa.GETPPV(forecast);
        Graph graph = new Graph();
        modelPanel.remove(1);
        modelPanel.repaint();
        JPanel ppvplot = graph.drawLineGraph("Power Output Forecast", "Time", "Power/kW", ppv);
        JPanel scatter = graph.drawScatter("GHI", "PV Power");
        scatter.setVisible(true);
        ppvplot.setVisible(true);
        modelPanel.setLayout(new BoxLayout(modelPanel, BoxLayout.PAGE_AXIS));
        modelPanel.add(scatter);
    }

    private void resetFormActionPerformed(java.awt.event.ActionEvent evt) {
        initControlPanel();
    }

    private void octopusDatePickerPropertyChange(java.beans.PropertyChangeEvent evt) {
        if (octopusDatePicker.getDate() == null) {
            return;
        }
        SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        try {
            Date date = format.parse(octopusDatePicker.getDate().toString());
            SimpleDateFormat newformat = new SimpleDateFormat("yyyy-MM-dd");
            String newdateStr = newformat.format(date);
            Calendar c = Calendar.getInstance();
            c.setTime(newformat.parse(newdateStr.toString()));
            c.add(Calendar.DATE, 1);
            String dateplus = newformat.format(c.getTime());
            AgilePricesOctopusAPI oa = new AgilePricesOctopusAPI();
            Graph graph = new Graph();
            String[][] data = oa.GETPrices(newdateStr + "T00:00Z", dateplus + "T00:00Z");
            JPanel octopus = graph.drawBarChart("Agile prices for: " + newdateStr, "Time", "Unit Rate", data);
            jPanel4.remove(1);
            jPanel4.setLayout(new BoxLayout(jPanel4, BoxLayout.PAGE_AXIS));
            jPanel4.add(octopus);
        } catch (Exception ex) {
            new Error("There was a problem finding data for this date.");
        }
    }

    private void chargedischargeRadioActionPerformed(java.awt.event.ActionEvent evt) {
        activePlots[4] = !activePlots[4];
        drawPowerGraph();
    }

    private void importRadioActionPerformed(java.awt.event.ActionEvent evt) {
        activePlots[1] = !activePlots[1];
        drawPowerGraph();
    }

    private void demandPowerRadioActionPerformed(java.awt.event.ActionEvent evt) {
        activePlots[3] = !activePlots[3];
        drawPowerGraph();
    }

    private void solarRadioActionPerformed(java.awt.event.ActionEvent evt) {
        activePlots[2] = !activePlots[2];
        drawPowerGraph();
    }

    private void exportRadioActionPerformed(java.awt.event.ActionEvent evt) {
        activePlots[0] = !activePlots[0];
        drawPowerGraph();
    }

    private void givEnergyDatePickerPropertyChange(java.beans.PropertyChangeEvent evt) {
        try {
            drawPowerGraph();
        } catch (Exception ex) {
            new Error("There was a problem finding data for this date.");
        }
    }

    private void mode2RadioActionPerformed(java.awt.event.ActionEvent evt) {
        if (mode2Radio.isSelected()) {
            mode1Radio.setSelected(false);
            mode3Radio.setSelected(false);
            mode4Radio.setSelected(false);
        }
    }

    private void drawPowerGraph() {
        try {
            if ((givEnergyDatePicker.getDate() == null) | loadComplete == false) {
                return;
            }
            SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
            Date date = format.parse(givEnergyDatePicker.getDate().toString());
            SimpleDateFormat newformat = new SimpleDateFormat("yyyy-MM-dd");
            String newdateStr = newformat.format(date);
            int length = activePlots.length;
            ChartsGivEnergyAPI cge = new ChartsGivEnergyAPI(null, null, newdateStr);
            DateStrings datestrs = cge.getDateStrs();
            Dictionary chartdata = cge.inverterValuesDictionary(datestrs.getDate());
            Graph graph = new Graph();
            String[] options = new String[]{"pacExport", "pacImport", "ppv", "loadpower", "batpoweractual"};
            String[] dataTitles = new String[]{"Export(W)", "Import(W)", "Solar Generation(W)", "Demanded Power(W)", "Charging/Discharging(W)"};
            double[][] parameters = new double[length][];
            for (int i = 0; i < activePlots.length; i++) {
                if (activePlots[i]) {
                    parameters[i] = chartdata.get(options[i]);
                } else {
                    dataTitles[i] = null;
                }
            }
            JPanel powerGraphDate = graph.drawLineGraph("Power for: " + datestrs.getDate(), "Time", "Power/W", dataTitles, cge.getTimevals(), parameters[0], parameters[1], parameters[2], parameters[3], parameters[4]);
            jPanel2.remove(1);
            jPanel2.repaint();
            jPanel2.setLayout(new BoxLayout(jPanel2, BoxLayout.PAGE_AXIS));
            jPanel2.add(powerGraphDate);
            jPanel2.repaint();
            jPanel2.revalidate();
        } catch (ParseException ex) {
            new Error("There was a problem displaying the graph.");
        }
    }

    public boolean validateDateFormat(String dateToValdate) {
        SimpleDateFormat formatter = new SimpleDateFormat("HHmm");
        formatter.setLenient(false);
        try {
            Date parsedDate = formatter.parse(dateToValdate);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            new Error("Error loading program, please restart program");
        }
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    private javax.swing.JPanel SystemControlPanel;

    private javax.swing.JPanel TomorrowRec;

    private javax.swing.JToggleButton automate;

    private javax.swing.JPanel azimuthPanel;

    private javax.swing.JPanel batteryInfo;

    private javax.swing.JTextField chargeStart;

    private javax.swing.JTextField chargeStop;

    private javax.swing.JTextField chargeUpTo;

    private javax.swing.JRadioButton chargedischargeRadio;

    private javax.swing.JPanel cloudOpacityPanel;

    private javax.swing.JPanel co2info;

    private javax.swing.JPanel consumptionInfo;

    private javax.swing.JPanel controlPanel;

    private javax.swing.JTextField cutoff;

    private javax.swing.JRadioButton demandPowerRadio;

    private javax.swing.JRadioButton exportRadio;

    private javax.swing.JPanel forecastingPanel;

    private javax.swing.JTabbedPane forecastingTabbedPane;

    private javax.swing.JPanel ghiPanel;

    private com.toedter.calendar.JDateChooser givEnergyDatePicker;

    private javax.swing.JPanel gridInfo;

    private javax.swing.JRadioButton importRadio;

    private javax.swing.JPanel invInfo;

    private javax.swing.JLabel jLabel1;

    private javax.swing.JLabel jLabel10;

    private javax.swing.JLabel jLabel11;

    private javax.swing.JLabel jLabel12;

    private javax.swing.JLabel jLabel13;

    private javax.swing.JLabel jLabel14;

    private javax.swing.JLabel jLabel15;

    private javax.swing.JLabel jLabel16;

    private javax.swing.JLabel jLabel17;

    private javax.swing.JLabel jLabel18;

    private javax.swing.JLabel jLabel19;

    private javax.swing.JLabel jLabel2;

    private javax.swing.JLabel jLabel21;

    private javax.swing.JLabel jLabel22;

    private javax.swing.JLabel jLabel23;

    private javax.swing.JLabel jLabel24;

    private javax.swing.JLabel jLabel25;

    private javax.swing.JLabel jLabel26;

    private javax.swing.JLabel jLabel27;

    private javax.swing.JLabel jLabel28;

    private javax.swing.JLabel jLabel29;

    private javax.swing.JLabel jLabel3;

    private javax.swing.JLabel jLabel30;

    private javax.swing.JLabel jLabel31;

    private javax.swing.JLabel jLabel32;

    private javax.swing.JLabel jLabel33;

    private javax.swing.JLabel jLabel34;

    private javax.swing.JLabel jLabel35;

    private javax.swing.JLabel jLabel36;

    private javax.swing.JLabel jLabel37;

    private javax.swing.JLabel jLabel38;

    private javax.swing.JLabel jLabel39;

    private javax.swing.JLabel jLabel4;

    private javax.swing.JLabel jLabel40;

    private javax.swing.JLabel jLabel44;

    private javax.swing.JLabel jLabel46;

    private javax.swing.JLabel jLabel47;

    private javax.swing.JLabel jLabel48;

    private javax.swing.JLabel jLabel49;

    private javax.swing.JLabel jLabel5;

    private javax.swing.JLabel jLabel50;

    private javax.swing.JLabel jLabel51;

    private javax.swing.JLabel jLabel52;

    private javax.swing.JLabel jLabel53;

    private javax.swing.JLabel jLabel54;

    private javax.swing.JLabel jLabel55;

    private javax.swing.JLabel jLabel56;

    private javax.swing.JLabel jLabel57;

    private javax.swing.JLabel jLabel58;

    private javax.swing.JLabel jLabel59;

    private javax.swing.JLabel jLabel6;

    private javax.swing.JLabel jLabel60;

    private javax.swing.JLabel jLabel61;

    private javax.swing.JLabel jLabel62;

    private javax.swing.JLabel jLabel63;

    private javax.swing.JLabel jLabel64;

    private javax.swing.JLabel jLabel7;

    private javax.swing.JLabel jLabel8;

    private javax.swing.JLabel jLabel9;

    private javax.swing.JPanel jPanel2;

    private javax.swing.JPanel jPanel3;

    private javax.swing.JPanel jPanel4;

    private javax.swing.JPanel jPanel6;

    private javax.swing.JSeparator jSeparator1;

    private javax.swing.JSeparator jSeparator10;

    private javax.swing.JSeparator jSeparator11;

    private javax.swing.JSeparator jSeparator12;

    private javax.swing.JSeparator jSeparator14;

    private javax.swing.JSeparator jSeparator15;

    private javax.swing.JSeparator jSeparator2;

    private javax.swing.JSeparator jSeparator3;

    private javax.swing.JSeparator jSeparator7;

    private javax.swing.JSeparator jSeparator8;

    private javax.swing.JSeparator jSeparator9;

    private javax.swing.JTabbedPane jTabbedPane1;

    private javax.swing.JTabbedPane jTabbedPane2;

    private javax.swing.JTabbedPane jTabbedPane3;

    private javax.swing.JTabbedPane jTabbedPane4;

    private javax.swing.JTextField latitude;

    private javax.swing.JPanel liveValuesPanel;

    private javax.swing.JLabel loading;

    private javax.swing.JTextField longitude;

    private javax.swing.JTabbedPane mainTabbedPane;

    private javax.swing.JRadioButton mode1Radio;

    private javax.swing.JRadioButton mode2Radio;

    private javax.swing.JRadioButton mode3Radio;

    private javax.swing.JTextField mode3start1;

    private javax.swing.JTextField mode3start2;

    private javax.swing.JTextField mode3stop1;

    private javax.swing.JTextField mode3stop2;

    private javax.swing.JRadioButton mode4Radio;

    private javax.swing.JTextField mode4start1;

    private javax.swing.JTextField mode4start2;

    private javax.swing.JTextField mode4stop1;

    private javax.swing.JTextField mode4stop2;

    private javax.swing.JLabel modeDisplay;

    private javax.swing.JPanel modelPanel;

    private com.toedter.calendar.JDateChooser octopusDatePicker;

    private javax.swing.JSlider panelAzimuthSlider;

    private javax.swing.JPanel panelDetailsPanel;

    private javax.swing.JSlider panelTiltSlider;

    private javax.swing.JPanel predictedPVPanel;

    private javax.swing.JPanel pvGeneration;

    private javax.swing.JPanel pvforecast;

    private javax.swing.JPanel recommendedsettingPanel;

    private javax.swing.JButton resetForm;

    private javax.swing.JButton saveChanges;

    private javax.swing.JButton saveChangesButton;

    private javax.swing.JPanel settingsPanel;

    private javax.swing.JToggleButton smartCharge;

    private javax.swing.JRadioButton solarRadio;

    private javax.swing.JPanel systemOverviewPanel;

    private javax.swing.JPanel todayRec;

    private javax.swing.JButton updateModel;

    private javax.swing.JPanel zenithPanel;

}
