package ssa_helpers

import (
	"fmt"
	"os"
	"path/filepath"
	"slices"

	"GoToJava/constants"
)

const (
	instruction_ = iota
	expression_
	value_
	expressionValue_
	type_
	instructionValue_

	none_ = -1
)

func GenerateJacoStructs(dirPath string) error {
	filePath := filepath.Join(".", dirPath, "ssaToJacoExpr.kt")
	file, err := os.Create(filePath)
	if err != nil {
		return err
	}

	_, err = file.Write([]byte(constants.PackageLine + ssaToJacoExpr))
	if err != nil {
		return err
	}

	filePath = filepath.Join(".", dirPath, "ssaToJacoValue.kt")
	file, err = os.Create(filePath)
	if err != nil {
		return err
	}

	_, err = file.Write([]byte(constants.PackageLine + ssaToJacoValue))
	if err != nil {
		return err
	}

	filePath = filepath.Join(".", dirPath, "ssaToJacoInst.kt")
	file, err = os.Create(filePath)
	if err != nil {
		return err
	}

	_, err = file.Write([]byte(constants.PackageLine + ssaToJacoInst))
	if err != nil {
		return err
	}

	filePath = filepath.Join(".", dirPath, "ssa_CallExpr.kt")
	file, err = os.Create(filePath)
	if err != nil {
		return err
	}

	_, err = file.Write([]byte(constants.PackageLine + ssaCallExpr))
	return err
}

func getJacoInterface(name string) int {
	if slices.Contains(instructions, name) {
		if slices.Contains(values, name) {
			return instructionValue_
		}
		return instruction_
	}
	if slices.Contains(types, name) {
		return type_
	}

	if slices.Contains(expressions, name) {
		if slices.Contains(values, name) {
			return expressionValue_
		}
		return expression_
	}

	if slices.Contains(values, name) {
		return value_
	}
	return -1
}

func AddImportAndDefinition(structDef, name string) string {
	structDef += jacoImport

	iface := getJacoInterface(name)

	if iface == instruction_ || iface == instructionValue_ {
		structDef += jacoInstImport
	}
	if iface == type_ {
		structDef += jacoTypeImport
	}

	if iface == none_ {
		structDef += fmt.Sprintf(constants.StructDefinition, name)
	} else {
		structDef += fmt.Sprintf(structDefinitionWithInterface, name, ifaceToStringMap[iface])
	}

	return structDef
}

func AddInterfaceFunctions(structDef, name string) string {
	if extra, ok := nameToExtra[name]; ok {
		structDef += extra
	}

	return structDef
}