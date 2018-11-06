let adminOctopus = {

    xsl: null,
    xml: null,

    xsltProcessor: null,

    init() {
        let self = adminOctopus;

        //Get XML
        adminModel.getAllBooks(data => {
            let domParser = new DOMParser();
            self.xml = domParser.parseFromString(data, 'text/xml');
        });

        //init xsltProcessor
        self.xsltProcessor = new XSLTProcessor();
        self.xsltProcessor.importStylesheet(self.xsl);


        //Init VIEW
        adminView.init();
        adminView.bindElements();
    },

    searchByApplyingXSL(searchValue) {
        let self = adminOctopus;

        self.xsltProcessor.setParameter(null, "searchValue", searchValue);

        return self.xsltProcessor.transformToFragment(self.xml, document);
    }
};