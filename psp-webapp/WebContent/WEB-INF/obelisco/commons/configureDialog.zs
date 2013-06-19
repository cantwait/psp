operacion = arg.get("aOperacion");
tipoFuncion = OperacionHelper.getType(operacion);

executeEditMode = (tipoFuncion == OperationType.MODIFICAR);
executeInsertMode = (tipoFuncion == OperationType.INCLUIR);
executeReadOnly = (tipoFuncion == OperationType.BUSCAR); 
