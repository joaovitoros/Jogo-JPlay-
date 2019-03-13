package Jogo;

import jplay.Keyboard;
import jplay.Scene;
import jplay.URL;
import jplay.Window;

public class Cenario1 {
	private Window janela;
	private Scene cena;
	private Jogador jogador;
	private Keyboard teclado;
	private Inimigo zumbi[];
	
	public Cenario1(Window window){
		janela = window;
		cena = new Scene();
		cena.loadFromFile(URL.scenario("Cenario.scn"));
		jogador = new Jogador(5, 500);
		teclado = janela.getKeyboard();
		zumbi = new Inimigo[100]; 
		
		Som.play("Celestial.wav");
		run();
	}
	
	
	private void run(){
		
		for (int i = 0; i < zumbi.length; i++) {
			zumbi[i] = new Inimigo(100*i,10*i);
		}
		while(true){

			jogador.controle(janela, teclado);
			jogador.caminho(cena);
			
			
			cena.moveScene(jogador);
			
			jogador.x += cena.getXOffset();
			jogador.y += cena.getYOffset();
			
			jogador.energia(janela);
			
			for (int i = 0; i < zumbi.length; i++) {
				zumbi[i].draw();
				zumbi[i].x += cena.getXOffset();
				zumbi[i].y += cena.getYOffset();
				zumbi[i].caminho(cena);
				zumbi[i].perseguir(jogador.x, jogador.y);
				
				zumbi[i].atacar(jogador);
				
				jogador.atirar(janela, cena, teclado, zumbi[i]);
				zumbi[i].morrer();
			}
			jogador.draw();
			janela.update();
			janela.delay(5);
		}
	}
}
