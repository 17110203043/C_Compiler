package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Menu;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
//������
public class MainFrame extends JFrame{
	public MainFrame() {
		//��ʼ����Ļ��С
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int width = screenSize.width;
		int height = screenSize.height;
		setSize(width/2,height/2);
		setTitle("СC���Ա�����");
		//���� 3��panel������
		Box box = Box.createVerticalBox();
		//���밴ť-->�Ϸ�
		JButton compilerButton = new JButton("����");
		JButton DAGButton = new JButton("DAG�Ż�");
		JPanel northPanel = new JPanel();
		northPanel.add(compilerButton);
		northPanel.add(DAGButton);
		box.add(northPanel);
		compilerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame showInfo = new JFrame("������Ϣչʾ");
				showInfo.setSize(900,1000);
				showInfo.setVisible(true);
			}
		});
		//����ı���-->�м�
		Text textArea = new Text(22, 100);
		JPanel textPanel = new JPanel();
		textPanel.add(textArea);
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setRowHeaderView(new LineNumberHeaderView());
		textPanel.add(scrollPane);
		box.add(textPanel);
		//�˵�
		MyMenu menuBar = new MyMenu(textArea);
		setJMenuBar(menuBar);
		setVisible(true);
		//������Ϣ
		JPanel errorPanel = new JPanel();
		JLabel errorInfo = new JLabel("������Ϣ:");
		errorPanel.add(errorInfo,BorderLayout.NORTH);
		box.add(errorPanel);
		add(box);
		//�ر��¼�
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				MyDialog d = new MyDialog(new JFrame(),menuBar);
				d.setVisible(true);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}

		});
	}
	//����Ի���
		class MyDialog extends JDialog{
			public MyDialog(JFrame frame,MyMenu m) {
				super(frame,"��ʾ",true);
				add(new JLabel("�Ƿ񱣴��ļ�"),BorderLayout.CENTER);
				JPanel panel = new JPanel();
				JButton ok = new JButton("����");
				JButton no = new JButton("������");
				no.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						setVisible(false);
					}
				});
	            ok.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						m.createFileDir();
						setVisible(false);
					}
				});
				panel.add(ok);
				panel.add(no);
				add(panel,BorderLayout.SOUTH);
				setSize(250, 150);
			}
		}
}
