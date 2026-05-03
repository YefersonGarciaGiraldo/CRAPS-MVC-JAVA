==================================================

CRAPS MVC / BY Yeferson Garcia
==================================================

Juego de Craps desarrollado en Java siguiendo el patrón de arquitectura MVC (Modelo - Vista - Controlador).

El proyecto simula una mesa de Craps permitiendo:

Registro de jugadores
Asignación automática del Tirador
Sistema de apuestas
Gestión de saldo
Resolución automática de apuestas
Flujo real de fases del juego
Resumen de resultados por ronda
El sistema fue desarrollado utilizando Java Swing para la interfaz gráfica.

==================================================

CARACTERISTICAS DEL SISTEMA
==================================================

Registro dinámico de jugadores
Gestión automática de turnos
Sistema de fichas
Validación de apuestas
Sistema de punto ON/OFF
Apuestas tipo Line Bet
Apuestas tipo One Shot
Resumen detallado de cada ronda
Visualización de apuestas activas
Cambio automático de Tirador
Eliminación de jugadores sin saldo
Reinicio automático del juego al finalizar
==================================================

INSTALACION: PRE REQUISITOS
==================================================

Para ejecutar el proyecto necesitas:

Java JDK 17 o superior
Eclipse IDE o cualquier editor compatible con Java
Sistema operativo Windows, Linux o MacOS
==================================================

INSTALACION
==================================================

Clona o descarga este repositorio.

Abre Eclipse.

Selecciona:

File -> Import -> Existing Projects into Workspace

Selecciona la carpeta del proyecto.

Espera a que Eclipse cargue las dependencias.

Ejecuta la clase principal del proyecto.

==================================================

ESTRUCTURA DEL PROYECTO
==================================================

juegoCraps/

├── Controlador/ │ └── Controlador principal del juego │ ├── Modelo/ │ ├── ModeloJuego │ ├── ModeloJugador │ └── ModeloApuesta │ ├── Vista/ │ ├── Interfaces gráficas │ ├── Tablero │ ├── Paneles │ └── Diálogos │ └── Interfaces/ └── Listeners y contratos de comunicación

==================================================

TECNOLOGIAS USADAS
==================================================

JAVA
JAVA SWING
PROGRAMACION ORIENTADA A OBJETOS
PROGRAMACION ORIENTADA A EVENTOS
PATRON MVC
==================================================

FUNCIONAMIENTO GENERAL
==================================================

Los jugadores se registran.
El primer jugador se convierte en Tirador.
Los jugadores realizan apuestas.
El Tirador lanza los dados.
El sistema resuelve automáticamente las apuestas.
Se muestran los resultados de la ronda.
El juego continúa hasta que quede un solo jugador.
==================================================

NOTAS
==================================================

Este proyecto fue desarrollado con fines académicos para practicar:

Arquitectura MVC
Eventos en Java Swing
Manejo de interfaces gráficas
Programación orientada a objetos
Programación orientada a eventos
Lógica de juegos de azar
==================================================

LICENCIA
==================================================

Proyecto desarrollado únicamente con fines educativos.

==================================================

AUTOR
==================================================

Proyecto desarrollado por:

Yeferson Alexander García Giraldo

GitHub: https://github.com/YefersonGarciaGiraldo
