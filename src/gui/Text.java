package gui;

import java.awt.Font;

import javax.swing.*;
//������������
public class Text extends JTextArea {
	public Text(int lines, int columns) {
		super("int main() {\n\n    return 0;\n}",lines,columns);
		setLineWrap(true);
		Font  f  = new Font(Font.SERIF,Font.PLAIN, 14);
		this.setFont(f);
	}
	
	//���ļ�������ʾ���ı���
	public void fileContent(String content) {
		//���������
		String str = getText();
		replaceRange("", 0, str.length());
		append(content);
	}
}
