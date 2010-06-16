package org.raap.domain

class IncomeLevelController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [incomeLevelInstanceList: IncomeLevel.list(params), incomeLevelInstanceTotal: IncomeLevel.count()]
    }

    def create = {
        def incomeLevelInstance = new IncomeLevel()
        incomeLevelInstance.properties = params
        return [incomeLevelInstance: incomeLevelInstance]
    }

    def save = {
        def incomeLevelInstance = new IncomeLevel(params)
        if (incomeLevelInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'incomeLevel.label', default: 'IncomeLevel'), incomeLevelInstance.id])}"
            redirect(action: "show", id: incomeLevelInstance.id)
        }
        else {
            render(view: "create", model: [incomeLevelInstance: incomeLevelInstance])
        }
    }

    def show = {
        def incomeLevelInstance = IncomeLevel.get(params.id)
        if (!incomeLevelInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'incomeLevel.label', default: 'IncomeLevel'), params.id])}"
            redirect(action: "list")
        }
        else {
            [incomeLevelInstance: incomeLevelInstance]
        }
    }

    def edit = {
        def incomeLevelInstance = IncomeLevel.get(params.id)
        if (!incomeLevelInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'incomeLevel.label', default: 'IncomeLevel'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [incomeLevelInstance: incomeLevelInstance]
        }
    }

    def update = {
        def incomeLevelInstance = IncomeLevel.get(params.id)
        if (incomeLevelInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (incomeLevelInstance.version > version) {
                    
                    incomeLevelInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'incomeLevel.label', default: 'IncomeLevel')] as Object[], "Another user has updated this IncomeLevel while you were editing")
                    render(view: "edit", model: [incomeLevelInstance: incomeLevelInstance])
                    return
                }
            }
            incomeLevelInstance.properties = params
            if (!incomeLevelInstance.hasErrors() && incomeLevelInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'incomeLevel.label', default: 'IncomeLevel'), incomeLevelInstance.id])}"
                redirect(action: "show", id: incomeLevelInstance.id)
            }
            else {
                render(view: "edit", model: [incomeLevelInstance: incomeLevelInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'incomeLevel.label', default: 'IncomeLevel'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def incomeLevelInstance = IncomeLevel.get(params.id)
        if (incomeLevelInstance) {
            try {
                incomeLevelInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'incomeLevel.label', default: 'IncomeLevel'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'incomeLevel.label', default: 'IncomeLevel'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'incomeLevel.label', default: 'IncomeLevel'), params.id])}"
            redirect(action: "list")
        }
    }
}
