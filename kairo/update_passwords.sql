-- Script para actualizar contraseñas a texto plano
-- Ejecutar este script en PostgreSQL (base de datos: Kairo, puerto: 5430)

-- Ver usuarios actuales
SELECT id, nombre, email, 
       CASE 
         WHEN LENGTH(password) > 20 THEN 'ENCRIPTADA'
         ELSE password 
       END as password_status
FROM usuario;

-- Actualizar TODAS las contraseñas a "123456" (texto plano)
UPDATE usuario SET password = '123456';

-- Verificar cambios
SELECT id, nombre, email, password FROM usuario;

-- Mensaje de confirmación
SELECT 'Contraseñas actualizadas exitosamente a: 123456' as mensaje;
