package edu.cgu.ist303.functions;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import edu.cgu.ist303.db.RoomRate;
import edu.cgu.ist303.db.RoomRateDataSource;
import edu.cgu.ist303.db.RoomType;
import edu.cgu.ist303.db.RoomTypeDataSource;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JButton;

public class MgtRoomPage extends JPanel implements ActionListener{
	private JTextField tfOldPricePeakWKD;
	private JTextField tfOldPricePeakWKN;
	private JTextField tfOldPriceNormalWKD;
	private JTextField tfOldPriceNormalWKN;
	private JTextField tfNewPricePeakWKD;
	private JTextField tfNewPricePeakWKN;
	private JTextField tfNewPriceNormalWKD;
	private JTextField tfNewPriceNormalWKN;
	private JComboBox comboBox;
	private HashMap<String, Integer> roomTypeMap;
	private JButton btnSubmit;
	/**
	 * Create the panel.
	 */
	public MgtRoomPage() {
		
		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(78)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 554, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(72, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(87)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 289, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(75, Short.MAX_VALUE))
		);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel label = new JLabel("Room Type");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(label, "4, 2, right, default");
		
		comboBox = new JComboBox();
		RoomTypeDataSource rtDS = new RoomTypeDataSource();
	    List<RoomType> roomTypeList = new ArrayList<RoomType>(); 
	    roomTypeList = rtDS.getAllRoom();
	    rtDS.close();
	    roomTypeMap = new HashMap<String, Integer>(); 
	    comboBox.addItem("-- Choose Room Type --");
	    for(RoomType rt:roomTypeList){
	      roomTypeMap.put(rt.getRoom_type(), rt.getId());
	      comboBox.addItem(rt.getRoom_type());
	    }
	    comboBox.addActionListener(this);
		panel.add(comboBox, "6, 2, fill, default");
		
		JLabel lblOriginalPrice = new JLabel("Original Price");
		lblOriginalPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblOriginalPrice, "6, 4, center, default");
		
		JLabel lblNewPrice = new JLabel("New Price");
		lblNewPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblNewPrice, "8, 4, center, default");
		
		JLabel lblPeakSeasonWeekday = new JLabel("Peak Season Weekday");
		lblPeakSeasonWeekday.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblPeakSeasonWeekday, "4, 6, right, default");
		
		tfOldPricePeakWKD = new JTextField();
		tfOldPricePeakWKD.setEditable(false);
		panel.add(tfOldPricePeakWKD, "6, 6, fill, default");
		tfOldPricePeakWKD.setColumns(10);
		
		tfNewPricePeakWKD = new JTextField();
		panel.add(tfNewPricePeakWKD, "8, 6, fill, default");
		tfNewPricePeakWKD.setColumns(10);
		
		JLabel lblPeakSeansonWeekend = new JLabel("Peak Season Weekend");
		lblPeakSeansonWeekend.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblPeakSeansonWeekend, "4, 8, right, default");
		
		tfOldPricePeakWKN = new JTextField();
		tfOldPricePeakWKN.setEditable(false);
		panel.add(tfOldPricePeakWKN, "6, 8, fill, default");
		tfOldPricePeakWKN.setColumns(10);
		
		tfNewPricePeakWKN = new JTextField();
		panel.add(tfNewPricePeakWKN, "8, 8, fill, default");
		tfNewPricePeakWKN.setColumns(10);
		
		JLabel lblNormalSeasonWeekday = new JLabel("Normal Season Weekday");
		lblNormalSeasonWeekday.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblNormalSeasonWeekday, "4, 10, right, default");
		
		tfOldPriceNormalWKD = new JTextField();
		tfOldPriceNormalWKD.setEditable(false);
		panel.add(tfOldPriceNormalWKD, "6, 10, fill, default");
		tfOldPriceNormalWKD.setColumns(10);
		
		tfNewPriceNormalWKD = new JTextField();
		panel.add(tfNewPriceNormalWKD, "8, 10, fill, default");
		tfNewPriceNormalWKD.setColumns(10);
		
		JLabel lblNormalSeasonWeekend = new JLabel("Normal Season Weekend");
		lblNormalSeasonWeekend.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblNormalSeasonWeekend, "4, 12, right, default");
		
		tfOldPriceNormalWKN = new JTextField();
		tfOldPriceNormalWKN.setEditable(false);
		panel.add(tfOldPriceNormalWKN, "6, 12, fill, default");
		tfOldPriceNormalWKN.setColumns(10);
		
		tfNewPriceNormalWKN = new JTextField();
		panel.add(tfNewPriceNormalWKN, "8, 12, fill, default");
		tfNewPriceNormalWKN.setColumns(10);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(this);
		panel.add(btnSubmit, "6, 16");
		setLayout(groupLayout);

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == comboBox  ){
			
			if(comboBox.getSelectedIndex()!=0){
				int room_type_id = 0;
				room_type_id = roomTypeMap.get(comboBox.getSelectedItem());
				RoomRateDataSource rrDS = new RoomRateDataSource();
				RoomRate rr = new RoomRate();
				rr = rrDS.getRoomTypeRateByRoomTypeId(room_type_id);
				rrDS.close();
				tfOldPricePeakWKD.setText(String.valueOf(rr.getPeak_weekday()));
				tfOldPricePeakWKN.setText(String.valueOf(rr.getPeak_weekend()));;
				tfOldPriceNormalWKD.setText(String.valueOf(rr.getOff_season_weekday()));
				tfOldPriceNormalWKN.setText(String.valueOf(rr.getOff_season_weekend()));
			}else
				;
		}
		
		if(e.getSource()== btnSubmit){
			int room_type_id = 0;
			room_type_id = roomTypeMap.get(comboBox.getSelectedItem());
			RoomRate rr = new RoomRate();
			RoomRateDataSource rrDS = new RoomRateDataSource();
			
			rr.setId(rrDS.getRoomRateIdByRoomTypeId(room_type_id)); //
			rr.setFk_room_type_id(room_type_id);
			if( tfNewPricePeakWKD.getText().isEmpty())
				rr.setPeak_weekday(Double.parseDouble(tfOldPricePeakWKD.getText()));
			else
				rr.setPeak_weekday(Double.parseDouble(tfNewPricePeakWKD.getText()));
			
			if( tfNewPricePeakWKN.getText().isEmpty())
				rr.setPeak_weekend(Double.parseDouble(tfOldPricePeakWKN.getText()));
			else
				rr.setPeak_weekend(Double.parseDouble(tfNewPricePeakWKN.getText()));
			
			if( tfNewPriceNormalWKD.getText().isEmpty())
				rr.setOff_season_weekday(Double.parseDouble(tfOldPriceNormalWKD.getText()));
			else
				rr.setOff_season_weekday(Double.parseDouble(tfNewPriceNormalWKD.getText()));
			
			if(  tfNewPriceNormalWKN.getText().isEmpty())
				rr.setOff_season_weekend(Double.parseDouble(tfOldPriceNormalWKN.getText()));
			else
				rr.setOff_season_weekend(Double.parseDouble(tfNewPriceNormalWKN.getText()));
			
			rrDS.updateRoomRate(rr);
			
			if(rrDS.updateRoomRate(rr))
				JOptionPane.showMessageDialog(this, "Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(this, "Failed", "Failed", JOptionPane.INFORMATION_MESSAGE);
			
			tfNewPricePeakWKD.setText("");
			tfNewPricePeakWKN.setText("");;
			tfNewPriceNormalWKD.setText("");
			tfNewPriceNormalWKN.setText("");
			
			rr = rrDS.getRoomTypeRateByRoomTypeId(room_type_id);
			rrDS.close();
			tfOldPricePeakWKD.setText(String.valueOf(rr.getPeak_weekday()));
			tfOldPricePeakWKN.setText(String.valueOf(rr.getPeak_weekend()));;
			tfOldPriceNormalWKD.setText(String.valueOf(rr.getOff_season_weekday()));
			tfOldPriceNormalWKN.setText(String.valueOf(rr.getOff_season_weekend()));
		}
		
	}
}
