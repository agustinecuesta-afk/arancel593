# Importa — control de costos

La interfaz ya está publicada en [Firebase Hosting](https://arancel593.web.app/). Firebase Authentication Email/Password está habilitado y la pantalla no ofrece registro público. El API todavía requiere crear un servicio web gratuito y conectarlo a la base PostgreSQL.

## Límites gratuitos elegidos

- **Firebase Hosting y Authentication:** se conservan en el proyecto existente.
- **Render Free:** ejecuta la API Java desde `render.yaml`. El servicio duerme tras 15 minutos sin tráfico, el primer acceso puede tardar cerca de un minuto y hay un cupo de horas mensuales compartido por el espacio de trabajo. Al exceder los límites gratuitos, Render puede suspender el servicio si no se agrega un medio de pago. No ofrece la disponibilidad de un servidor de pago.
- **Neon Free:** PostgreSQL gratuito permanente, con hasta 100 CU-horas y 1 GB por proyecto. Suspende el cómputo tras inactividad; los límites, condiciones y disponibilidad pueden cambiar.

Estas opciones no crean servicios ni cargos desde este repositorio. Referencias oficiales: [Render Free](https://render.com/docs/free), [Neon Free](https://neon.com/pricing), [Firebase pricing](https://firebase.google.com/pricing).

## Preparación única de cuentas

1. En Neon, crea un proyecto gratuito en **AWS US East (Ohio)** (`aws-us-east-2`) y copia sus datos de conexión. Esa región coincide con la definida en `render.yaml`.
2. En Google Cloud Console del proyecto `arancel593`, crea una cuenta de servicio exclusiva para verificar los ID tokens de Firebase. Descarga su archivo JSON privado. No lo subas a Git ni lo envíes por correo o chat.
3. En Firebase Console, abre **Authentication → Users → Add user** y crea `agustincuesta1975@gmail.com` con una contraseña segura.
4. En Render, crea un **Blueprint** desde el repositorio del proyecto y selecciona el plan Free. `autoDeploy` está desactivado: confirma cada despliegue manualmente.
5. En Render, configura las variables secretas que solicita el Blueprint:
   - `DB_USER`: el rol PostgreSQL de Neon.
   - `DB_PASSWORD`: contraseña de ese rol.
   - `JDBC_DATABASE_URL`: `jdbc:postgresql://<host-neon>/<base>?sslmode=require` (usa el hostname y base que muestra Neon).
   - `FIREBASE_SERVICE_ACCOUNT_JSON`: contenido completo del JSON privado de la cuenta de servicio, ingresado directamente en Render.
6. Despliega manualmente el servicio. Prueba `https://<servicio>.onrender.com/actuator/health` y confirma que responde `{"status":"UP"}`.
7. Copia la URL HTTPS real del servicio en `public/config.js`, por ejemplo `window.IMPORTACION_API_BASE = "https://<servicio>.onrender.com";`. Despliega Hosting: `firebase deploy --only hosting --project arancel593`.
8. Abre [Firebase Hosting](https://arancel593.web.app/), inicia sesión con la cuenta autorizada y verifica el correo. El API valida el ID token, el correo verificado y la lista permitida.

No compartas ni pegues aquí contraseñas de Neon ni archivos JSON de cuentas de servicio. Si se pierde o filtra una clave privada, revócala y genera otra desde Google Cloud.

## Desarrollo local

```cmd
mvnw.cmd test
mvnw.cmd spring-boot:run
```

El perfil local sigue utilizando H2 en memoria y datos de demostración. El filtro Firebase se activa únicamente en el perfil `prod`. Los datos de H2 que ya existían en desarrollo no se migran a Neon; la primera base de producción comenzará vacía.
