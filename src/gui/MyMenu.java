package gui;


import java.awt.BorderLayout;
//�˵� �ļ�����
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileFilter;


public class MyMenu extends JMenuBar{
	private Text temp;
	private	String fileContent = new String();
	//�����ļ��¼�
	class LeadingFileAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			createFileDialog();
		}
		
	}
	//�����ļ��¼�
	class SaveFileAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			createFileDir();
		}
		
	}
	public MyMenu(Text f) {
		temp = f;
		JMenu startMenu= new JMenu("�ļ�");
		JMenuItem leadingFile= new JMenuItem("�����ļ�");
		JMenuItem saveFile = new JMenuItem("�����ļ�");
		startMenu.add(leadingFile);
		startMenu.add(saveFile);
		add(startMenu);
		//�������ļ��󶨵����ļ����¼�
		LeadingFileAction leadingEvent = new LeadingFileAction();
		leadingFile.addActionListener(leadingEvent);
		//�������ļ������¼�
		SaveFileAction saveFileAction = new SaveFileAction();
		saveFile.addActionListener(saveFileAction);
	}
	
	//����ѡ���ļ��Ի���-->ѡ���ļ�
	public void createFileDialog() {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("."));
		chooser.setVisible(true);
		chooser.setSize(300,200 );
		chooser.showOpenDialog(temp);//չʾ�ļ���
		if(chooser.getSelectedFile()==null) {
			return;
		}
		String filename = chooser.getSelectedFile().getPath();
		//���ļ���Ϣ�����ı���
		FileOperator fo = new FileOperator();
		temp.fileContent(fo.leadingFile(filename));
	}
	
	//�����ļ�����Ի���-->ѡ��Ŀ¼����
	public void createFileDir() {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("."));
		chooser.setSize(300,200 );
		chooser.setFileFilter(new FileFilter() {
			
			@Override
			public String getDescription() {
				// TODO Auto-generated method stub
				return "Ŀ¼";
			}
			
			@Override
			public boolean accept(File f) {
				// TODO Auto-generated method stub
				return f.isDirectory();
			}
		});
		chooser.showSaveDialog(null);//չʾ�ļ���
		//�����ļ��ľ���·��
		if(chooser.getSelectedFile()!=null) {
			String filename = chooser.getSelectedFile().getAbsolutePath();
			FileOperator fo = new FileOperator();
			//���ж���Դ�ļ��Ƿ��ȵ�
			if(!fo.isEqual(filename,temp)) {
				fo.save(filename,temp);
				MyDialog d = new MyDialog(new JFrame(),"����ɹ�");
				d.setVisible(true);
			}else {
				MyDialog d = new MyDialog(new JFrame(),"�ļ�û�и���");
				d.setVisible(true);
			}
		}
	}
	
	//����Ի���
	class MyDialog extends JDialog{
		public MyDialog(JFrame frame,String info) {
			super(frame,"��ʾ",true);
			add(new JLabel(info),BorderLayout.CENTER);
			JPanel panel = new JPanel();
			JButton ok = new JButton("ȷ��");
			ok.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					setVisible(false);
				}
			});
			panel.add(ok);
			add(panel,BorderLayout.SOUTH);
			setSize(250, 150);
		}
	}
}
