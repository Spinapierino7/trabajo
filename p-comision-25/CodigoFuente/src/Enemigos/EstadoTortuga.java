package Enemigos;

import Fabricas.Sprite;
import Visitor.Visitor;

interface EstadoTortuga {
   			void golpeadoPorMario();
		    int variarVelocidad();
		    //sprites
		    Sprite getTortugaDer();
			Sprite getTortugaIzq();
			void recibirGolpeBola();
		
}
