--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.4
-- Dumped by pg_dump version 9.5.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: clients; Type: TABLE; Schema: public; Owner: Sheena
--

CREATE TABLE clients (
    id integer NOT NULL,
    firstname character varying,
    lastname character varying,
    notes character varying,
    stylistid integer
);


ALTER TABLE clients OWNER TO "Sheena";

--
-- Name: clients_id_seq; Type: SEQUENCE; Schema: public; Owner: Sheena
--

CREATE SEQUENCE clients_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE clients_id_seq OWNER TO "Sheena";

--
-- Name: clients_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Sheena
--

ALTER SEQUENCE clients_id_seq OWNED BY clients.id;


--
-- Name: stylists; Type: TABLE; Schema: public; Owner: Sheena
--

CREATE TABLE stylists (
    id integer NOT NULL,
    firstname character varying,
    lastname character varying,
    description character varying
);


ALTER TABLE stylists OWNER TO "Sheena";

--
-- Name: stylists_id_seq; Type: SEQUENCE; Schema: public; Owner: Sheena
--

CREATE SEQUENCE stylists_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE stylists_id_seq OWNER TO "Sheena";

--
-- Name: stylists_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Sheena
--

ALTER SEQUENCE stylists_id_seq OWNED BY stylists.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Sheena
--

ALTER TABLE ONLY clients ALTER COLUMN id SET DEFAULT nextval('clients_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Sheena
--

ALTER TABLE ONLY stylists ALTER COLUMN id SET DEFAULT nextval('stylists_id_seq'::regclass);


--
-- Data for Name: clients; Type: TABLE DATA; Schema: public; Owner: Sheena
--

COPY clients (id, firstname, lastname, notes, stylistid) FROM stdin;
2	Jessica	Rabbit	Visits once a month. Always same bob cut with bangs.	1
11	Phil	Dang	New client	7
12	Lily	Harrison	New client	4
1	Jane	Smith	Has visited two times. Used color Como Light Brown 7NGM.	1
13	Elizabeth	Phan	Very thick hair that does not perm easily. Leave on for longer than usual.	7
15	Adam	Walker	Janet's first customer.	12
16	Ryan	Wheatley	Loyal customer who has been going to Kelly for 5+ years.	15
17	Rachel	Johnston	Loves variety and vibrant colors - gets a new color every time.	15
18	Lindsey	Smith	Enjoys reading magazines. Likes her coffee black.	15
5	Sheena	Do	New client	1
\.


--
-- Name: clients_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Sheena
--

SELECT pg_catalog.setval('clients_id_seq', 18, true);


--
-- Data for Name: stylists; Type: TABLE DATA; Schema: public; Owner: Sheena
--

COPY stylists (id, firstname, lastname, description) FROM stdin;
7	Kim	Nguyen	Kim is a Portland native, and has been working with us for over 5 years.
4	Suzie	Styles	Suzie specializes in styling women's hair for special occasions, such as weddings.
16	Jennifer	Williams	New stylist
15	Kelly	Liao	Kelly is one of our veteran stylists and has been with us for over 10 years.
12	Janet	Smith	Janet is a recent graduate of Paul Mitchell beauty school in Portland.
1	John	Doe	John specializes in hair color and balayage highlights.
\.


--
-- Name: stylists_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Sheena
--

SELECT pg_catalog.setval('stylists_id_seq', 19, true);


--
-- Name: clients_pkey; Type: CONSTRAINT; Schema: public; Owner: Sheena
--

ALTER TABLE ONLY clients
    ADD CONSTRAINT clients_pkey PRIMARY KEY (id);


--
-- Name: stylists_pkey; Type: CONSTRAINT; Schema: public; Owner: Sheena
--

ALTER TABLE ONLY stylists
    ADD CONSTRAINT stylists_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: Sheena
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM "Sheena";
GRANT ALL ON SCHEMA public TO "Sheena";
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

