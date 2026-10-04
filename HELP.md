# Importa — control de costos

La interfaz ya está publicada en [Firebase Hosting](https://arancel593.web.app/) y no requiere iniciar sesión. El API todavía requiere crear un servicio web gratuito y conectarlo a la base PostgreSQL.

## Acceso público de solo lectura

El sitio y los endpoints GET del API son públicos: cualquiera puede consultar los costos registrados y buscar en el arancel. En producción, el filtro del API responde `405 Method Not Allowed` a todas las solicitudes que no sean GET. No se permite crear, editar ni eliminar registros por Internet. No publiques información de costos que no quieras hacer visible a cualquier persona.

## Límites gratuitos elegidos

- **Firebase Hosting:** permanece en el proyecto existente. El panel y el API no usan inicio de sesión.
- **Render Free:** ejecuta la API Java desde `render.yaml`. El servicio duerme tras 15 minutos sin tráfico, el primer acceso puede tardar cerca de un minuto y hay un cupo de horas mensuales compartido por el espacio de trabajo. Al exceder los límites gratuitos, Render puede suspender el servicio si no se agrega un medio de pago. No ofrece la disponibilidad de un servidor de pago.
- **Neon Free:** PostgreSQL gratuito permanente, con hasta 100 CU-horas y 1 GB por proyecto. Suspende el cómputo tras inactividad; los límites, condiciones y disponibilidad pueden cambiar.

Estas opciones no crean servicios ni cargos desde este repositorio. Referencias oficiales: [Render Free](https://render.com/docs/free), [Neon Free](https://neon.com/pricing), [Firebase pricing](https://firebase.google.com/pricing).

## Despliegue del API

1. Inicia sesión o crea un proyecto en [Neon](https://console.neon.tech/) con el plan Free. Selecciona **AWS US East (Ohio)** (`aws-us-east-2`) y copia los datos de conexión; coincide con la región definida en `render.yaml`.
2. Inicia sesión o crea tu cuenta gratuita en [Render](https://dashboard.render.com/register) con GitHub y autoriza acceso al repositorio privado `agustinecuesta-afk/arancel593`.
3. En Render, crea un **Blueprint** en [New → Blueprint](https://dashboard.render.com/blueprints/new) usando ese repositorio y la rama `main`. El archivo `render.yaml` prepara el servicio `importacion-costos-api` en el plan Free de Ohio. `autoDeploy` está desactivado: confirma cada despliegue manualmente.
4. En Render, configura las variables que solicita el Blueprint:
   - `DB_USER`: el rol PostgreSQL de Neon.
   - `DB_PASSWORD`: contraseña de ese rol.
   - `JDBC_DATABASE_URL`: `jdbc:postgresql://<host-neon>/<base>?sslmode=require` (usa el hostname y base que muestra Neon).
5. Despliega manualmente el servicio. Prueba `https://<servicio>.onrender.com/actuator/health` y confirma que responde `{"status":"UP"}`.
6. Copia la URL HTTPS real del servicio en `public/config.js`, por ejemplo `window.IMPORTACION_API_BASE = "https://<servicio>.onrender.com";`. Despliega Hosting: `firebase deploy --only hosting --project arancel593`.

No compartas ni pegues aquí la contraseña de Neon.

## Desarrollo local

```cmd
mvnw.cmd test
mvnw.cmd spring-boot:run
```

El perfil local sigue utilizando H2 en memoria y datos de demostración; sus rutas de escritura solo funcionan localmente. En producción no se cargan datos de demostración y las rutas de escritura se bloquean. Los datos de H2 que ya existían en desarrollo no se migran a Neon; la primera base de producción comenzará vacía.
